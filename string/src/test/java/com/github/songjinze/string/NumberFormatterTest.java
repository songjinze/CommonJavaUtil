package com.github.songjinze.string;

import static org.junit.Assert.*;

public class NumberFormatterTest {

    @org.junit.Test
    public void getArabicFromChinese() {
        NumberFormatter numberFormatter=new NumberFormatter();
        assertEquals("0",numberFormatter.getArabicFromChinese("零"));
        assertEquals("15",numberFormatter.getArabicFromChinese("十五"));
        assertEquals("10",numberFormatter.getArabicFromChinese("十"));
        assertEquals("90",numberFormatter.getArabicFromChinese("九十"));
        assertEquals("155",numberFormatter.getArabicFromChinese("一百五十五"));
        assertEquals("1234",numberFormatter.getArabicFromChinese("一千二百三十四"));
        assertEquals("11551",numberFormatter.getArabicFromChinese("一万一千五百五十一"));
        assertEquals("234561543",numberFormatter.getArabicFromChinese("二亿三千四百五十六万一千五百四十三"));
        assertEquals("2000010",numberFormatter.getArabicFromChinese("二百万零一十"));
    }
}