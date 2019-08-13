package com.github.songjinze.commonjavautil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author songjinze
 */
public class NumberFormatter {
    private final Map<Character, Character> digitsFromChineseToArabic = new HashMap<Character, Character>();
    private final Map<Character, Integer> chineseNumberPositions = new HashMap<Character, Integer>();
    private final Map<Character, Character> digitsFromArabicToChinese = new HashMap<Character, Character>();

    public NumberFormatter() {
        digitsFromChineseToArabic.put('零', '0');
        digitsFromChineseToArabic.put('一', '1');
        digitsFromChineseToArabic.put('二', '2');
        digitsFromChineseToArabic.put('三', '3');
        digitsFromChineseToArabic.put('四', '4');
        digitsFromChineseToArabic.put('五', '5');
        digitsFromChineseToArabic.put('六', '6');
        digitsFromChineseToArabic.put('七', '7');
        digitsFromChineseToArabic.put('八', '8');
        digitsFromChineseToArabic.put('九', '9');
        chineseNumberPositions.put('十', 1);
        chineseNumberPositions.put('百', 2);
        chineseNumberPositions.put('千', 3);
        chineseNumberPositions.put('万', 4);
        chineseNumberPositions.put('亿', 8);
        digitsFromArabicToChinese.put('0', '零');
        digitsFromArabicToChinese.put('1', '一');
        digitsFromArabicToChinese.put('2', '二');
        digitsFromArabicToChinese.put('3', '三');
        digitsFromArabicToChinese.put('4', '四');
        digitsFromArabicToChinese.put('5', '五');
        digitsFromArabicToChinese.put('6', '六');
        digitsFromArabicToChinese.put('7', '七');
        digitsFromArabicToChinese.put('8', '八');
        digitsFromArabicToChinese.put('9', '九');
    }

    /**
     * 将中文简体表示数字的字符转换为阿拉伯数字
     * 支持亿级数字及亿级以下
     * 支持中文字符只包括{'一','二','三','四','五','六','七','八','九','十','百','千','万','亿'}
     * 字符请使用UTF-8格式
     * <p>
     * 例：
     * 十二亿六千四百万三千二百零八 : 1264003208
     * <p>
     * 反向转化请参照{@link NumberFormatter}
     *
     * @param numberInChinese 中文简体表示的数字
     * @return 阿拉伯数字表示
     * @throws RuntimeException 有非法字符
     */
    public String getArabicFromChinese(String numberInChinese) throws RuntimeException {
        char[] charArray = numberInChinese.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        int positionIndex = 0;
        for (int i = charArray.length - 1; i >= 0; i--) {
            char c = charArray[i];
            Character digit = digitsFromChineseToArabic.get(c);
            if (digit == null) {  //非数字
                Integer position = chineseNumberPositions.get(c);
                if (position == null) {  //非位描述
                    throw new RuntimeException("Illegal Character found!");
                } else {
                    if (positionIndex <= position) {
                        positionIndex = position;
                    } else {
                        position = positionIndex + position;
                    }
                    for (; index < position; index++) {
                        stringBuilder.append('0');
                    }
                    index = position;
                }
            } else {
                stringBuilder.append(digit);
                index++;
            }
        }
        if (charArray[0] == '十') {
            stringBuilder.append('1');
        }
        return stringBuilder.reverse().toString();
    }

    /**
     * 将阿拉伯数字表示的数字转换为中文简体
     * 支持亿级数字及以下
     * 支持阿拉伯数字字符只包括{'1','2','3','4','5','6','7','8','9','0'}
     * 字符请使用UTF-8格式
     * <p>
     * 例:
     * 1264003208 : 十二亿六千四百万三千二百零八
     * <p>
     * 反向转化请参照{@link NumberFormatter}
     *
     * @param numberInArabic 阿拉伯数字表示的数字
     * @return 中文简体表示的数字
     * @throws RuntimeException 有非法字符
     */
    private String getChineseFromArabic(String numberInArabic) throws RuntimeException {
        char[] charArray = numberInArabic.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = charArray.length - 1; i >= 0; i--) {
            char c=charArray[i];
            Character digitChinese=digitsFromArabicToChinese.get(c);
            
        }
        // TODO
        return null;
    }
}
