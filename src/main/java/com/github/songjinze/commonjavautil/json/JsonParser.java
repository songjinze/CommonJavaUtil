package com.github.songjinze.commonjavautil.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonParser {

    public static String getValueFromJson(String json, String key) {
        JsonObject jsonObject = parseJsonToObject(json);
        return jsonObject.get(key).getAsString();
    }

    public static JsonObject getObjectFromJson(String json, String objectName) {
        JsonObject jsonObject = parseJsonToObject(json);
        return jsonObject.get(objectName).getAsJsonObject();
    }

    public static JsonObject parseJsonToObject(String json) {
        return parseJsonToElement(json).getAsJsonObject();
    }

    public static JsonElement parseJsonToElement(String json){
        return new com.google.gson.JsonParser().parse(json);
    }

}
