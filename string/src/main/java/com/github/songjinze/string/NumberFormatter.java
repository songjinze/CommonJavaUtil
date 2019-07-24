package com.github.songjinze.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author songjinze
 */
public class NumberFormatter {
    private final Map<Character, Integer> digit = new HashMap<Character, Integer>();
    private final Map<Character, Integer> position = new HashMap<Character, Integer>();

    public NumberFormatter() {
        digit.put('零', 0);
        digit.put('一', 1);
        digit.put('二', 2);
        digit.put('三', 3);
        digit.put('四', 4);
        digit.put('五', 5);
        digit.put('六', 6);
        digit.put('七', 7);
        digit.put('八', 8);
        digit.put('九', 9);
        position.put('十', 1);
        position.put('百', 2);
        position.put('千', 3);
        position.put('万', 4);
        position.put('亿', 5);
    }

    /**
     * 将中文简体表示数字的字符转换为阿拉伯数字
     * 支持亿级数字及亿级以下
     *
     * @param numberInChinese 中文简体表示的数字
     * @return 阿拉伯数字表示
     * @throws RuntimeException
     */
    public synchronized String getArabicFromChinese(String numberInChinese) throws RuntimeException {
        char[] charArray=numberInChinese.toCharArray();
        StringBuilder stringBuilder=new StringBuilder();
        for(char c:charArray){

        }
        return null;
    }
}
