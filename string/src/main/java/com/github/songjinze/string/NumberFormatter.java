package com.github.songjinze.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author songjinze
 */
public class NumberFormatter {
    private final Map<Character, Character> digits = new HashMap<Character, Character>();
    private final Map<Character, Integer> positions = new HashMap<Character, Integer>();

    public NumberFormatter() {
        digits.put('零', '0');
        digits.put('一', '1');
        digits.put('二', '2');
        digits.put('三', '3');
        digits.put('四', '4');
        digits.put('五', '5');
        digits.put('六', '6');
        digits.put('七', '7');
        digits.put('八', '8');
        digits.put('九', '9');
        positions.put('十', 1);
        positions.put('百', 2);
        positions.put('千', 3);
        positions.put('万', 4);
        positions.put('亿', 8);
    }

    /**
     * 将中文简体表示数字的字符转换为阿拉伯数字
     * 支持亿级数字及亿级以下
     * 支持中文字符只包括{'一','二','三','四','五','六','七','八','九','十','百','千','万','亿'}
     *
     * 十二亿六千四百万三千二百零八 : 1264003208
     *
     * @param numberInChinese 中文简体表示的数字
     * @return 阿拉伯数字表示
     * @throws RuntimeException
     */
    public synchronized String getArabicFromChinese(String numberInChinese) throws RuntimeException {
        char[] charArray=numberInChinese.toCharArray();
        StringBuilder stringBuilder=new StringBuilder();
        int index=0;
        int positionIndex=0;
        for(int i=charArray.length-1;i>=0;i--){
            char c=charArray[i];
            Character digit=digits.get(c);
            if(digit==null){  //非数字
                Integer position=positions.get(c);
                if(position == null){  //非位描述
                    throw new RuntimeException("Illegal Character found!");
                }else{
                    if(positionIndex<=position){
                        positionIndex=position;
                    }else{
                        position=positionIndex+position;
                    }
                    for(;index<position;index++){
                        stringBuilder.append('0');
                    }
                    index=position;
                }
            }else{
                stringBuilder.append(digit);
                index++;
            }
        }
        if(charArray[0]=='十'){
            stringBuilder.append('1');
        }
        return stringBuilder.reverse().toString();
    }
}
