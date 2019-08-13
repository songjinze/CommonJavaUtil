package com.github.songjinze.commonjavautil.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.*;

public class JsonAttributeGetter {

    private final String separator;

    public JsonAttributeGetter(String separator) {
        this.separator = separator;
    }


    /**
     * 根据当前path得到json中的元素值
     * @param path 目标元素对于当前json element的path值
     * @param jsonElement 当前开始查找的json element, 方法内部会进行去除起始列表的操作
     * @return 当前元素值,为List(如果书写正确应该只找到一个)
     *         null 如果当前元素没有找到
     */
    public List<String> getAttributeFromElementByPath(String path,JsonElement jsonElement){
        String[]names=path.split(separator);
        List<JsonElement>jsonElements=new ArrayList<>();
        jsonElements.add(jsonElement);
        List<JsonObject> jsonObjects=rmJsonArrays(jsonElements);
        List<JsonObject> jsonObjects1=getObjectsFromObjects(names,jsonObjects,0);
        List<String>res=new ArrayList<>();
        for(JsonObject jsonObject1:jsonObjects1){
            getAttributesFromJsonObject(names[names.length-1],jsonObject1,res);
        }
        return res;
    }

    /**
     *
     * @param jsonElement
     * @param path
     * @return
     */
    public JsonElement getJsonElementFromElement(JsonElement jsonElement,String path){
        String[]names=path.split(separator);
        List<JsonElement> originElement=new ArrayList<>();
        originElement.add(jsonElement);
        List<JsonObject> jsonObjects=getObjectsFromObjects(names,rmJsonArrays(originElement),0);
        String elementName= names[names.length-1];
        for(JsonObject jsonObject:jsonObjects){
            Set<Map.Entry<String,JsonElement>> entrySet=jsonObject.getAsJsonObject().entrySet();
            for(Map.Entry<String,JsonElement> elementEntry:entrySet){
                if(elementEntry.getKey().equals(elementName)){
                    return elementEntry.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 通过路径获得当前路径对应的json array
     *
     * @param path com.github.songjinze.commonjavautil.json array对应的路径
     * @param json 爬取页面的json
     * @return 当前路径对应的JsonArray
     * @throws RuntimeException 当前路径对应的不是JsonArray
     */
    public List<JsonArray> getArrayFromPath(String path, String json) throws RuntimeException {
        String[] names = path.split(separator);
        JsonElement jsonElement = JsonParser.parseJsonToElement(json);
        List<JsonElement> originObjects = new ArrayList<>();
        originObjects.add(jsonElement);
        List<JsonObject> jsonObjects = getObjectsFromObjects(names, rmJsonArrays(originObjects), 0);
        List<JsonArray> jsonArrays = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            String arrayName=names[names.length-1];
            Set<Map.Entry<String,JsonElement>> entries=jsonObject.getAsJsonObject().entrySet();
            for(Map.Entry<String,JsonElement> elementEntry:entries){
                if(elementEntry.getKey().equals(arrayName) && elementEntry.getValue().isJsonArray()) jsonArrays.add(elementEntry.getValue().getAsJsonArray());
            }
        }
        return jsonArrays;
    }


    /**
     * 得到json中所有的指定path的attribute
     *
     * @param path attribute名字
     * @param json 爬取页面的json
     * @return attribute列表
     */
    public List<String> getAllAttributes(String path, String json) {
        JsonElement jsonElement = JsonParser.parseJsonToElement(json);
        return getAttributeFromElementByPath(path,jsonElement);
    }

    /**
     * 得到第一个attribute
     *
     * @param name attribute名字
     * @param json 爬取页面的json
     * @return 第一个attribute的值
     */
    public String getAttributesToAppend(String name, String json) {
        return getAllAttributes(name, json).get(0);
    }

    private List<JsonObject> getObjectsFromObjects(String[] name, List<JsonObject> jsonObjects, int index) {
        if (index == name.length - 1) {
            return jsonObjects;
        }
        List<JsonObject> res = new ArrayList<>();
        for (JsonObject jsonObject1 : jsonObjects) {
            Set<Map.Entry<String, JsonElement>> entrySet = jsonObject1.entrySet();
            for (Map.Entry<String, JsonElement> entry : entrySet) {
                if (entry.getKey().equals(name[index])) {
                    if (entry.getValue().isJsonArray()) {
                        JsonArray jsonElements = entry.getValue().getAsJsonArray();
                        for (JsonElement jsonElement : jsonElements) {
                            if (jsonElement.isJsonObject()) {
                                res.add(jsonElement.getAsJsonObject());
                            }
                        }
                    } else if (entry.getValue().isJsonObject()) {
                        res.add(entry.getValue().getAsJsonObject());
                    }
                }
            }
        }
        return getObjectsFromObjects(name, res, index + 1);
    }

    private List<String> getAttributesFromJsonObject(String name, JsonObject jsonObject, List<String> res) {
        Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
        for (Map.Entry<String, JsonElement> entry : entrySet) {
            JsonElement attributeValue = entry.getValue();
            String key = entry.getKey();
            if (key.equals(name)) {
                if (!attributeValue.isJsonArray() && !attributeValue.isJsonObject()) {
                    res.add(attributeValue.getAsString());
                }
            } else {
                if (attributeValue.isJsonArray()) {
                    JsonArray jsonElements = attributeValue.getAsJsonArray();
                    for (JsonElement jsonElement : jsonElements) {
                        if (jsonElement.isJsonObject()) {
                            res = (getAttributesFromJsonObject(name, jsonElement.getAsJsonObject(), res));
                        }
                    }
                } else if (attributeValue.isJsonObject()) {
                    res = (getAttributesFromJsonObject(name, attributeValue.getAsJsonObject(), res));
                }
            }
        }
        return res;
    }

    /**
     * 获取element下所有非列表元素，如果有列表则返回列表中最上层非列表元素
     * @param originObjects
     * @return
     */
    public List<JsonObject> rmJsonArrays(List<JsonElement> originObjects) {
        boolean allNotArray = false;
        List<JsonElement> tempJsonArrays = new ArrayList<>();
        List<JsonObject> tempJsonObjects = new ArrayList<>();
        while (!allNotArray) {
            for (JsonElement jsonElement1 : originObjects) {
                if (jsonElement1.isJsonArray()) tempJsonArrays.add(jsonElement1);
                else tempJsonObjects.add((JsonObject) jsonElement1);
            }
            originObjects.clear();
            if (tempJsonArrays.isEmpty()) allNotArray = true;
            else {
                for (JsonElement jsonElement11 : tempJsonArrays) {
                    for (JsonElement jsonElement1 : jsonElement11.getAsJsonArray()) {
                        originObjects.add(jsonElement1);
                    }
                }
                tempJsonArrays.clear();
            }
        }
        return tempJsonObjects;
    }
}
