package com.github.songjinze.string;

import org.junit.Test;

import static org.junit.Assert.*;

public class NumberFormatterTest {

    private NumberFormatter numberFormatter = new NumberFormatter();

    @org.junit.Test
    public void getArabicFromChinese() {
        assertEquals("0", numberFormatter.getArabicFromChinese("零"));
        assertEquals("15", numberFormatter.getArabicFromChinese("十五"));
        assertEquals("10", numberFormatter.getArabicFromChinese("十"));
        assertEquals("90", numberFormatter.getArabicFromChinese("九十"));
        assertEquals("155", numberFormatter.getArabicFromChinese("一百五十五"));
        assertEquals("1234", numberFormatter.getArabicFromChinese("一千二百三十四"));
        assertEquals("11551", numberFormatter.getArabicFromChinese("一万一千五百五十一"));
        assertEquals("234561543", numberFormatter.getArabicFromChinese("二亿三千四百五十六万一千五百四十三"));
        assertEquals("23434561543",numberFormatter.getArabicFromChinese("二百三十四亿三千四百五十六万一千五百四十三"));
        assertEquals("2000010", numberFormatter.getArabicFromChinese("二百万零一十"));
    }

//    @Test
//    public void getChineseFromArabic() {
//        assertEquals("零", numberFormatter.getChineseFromArabic("0"));
//        assertEquals("十五", numberFormatter.getChineseFromArabic("15"));
//        assertEquals("十", numberFormatter.getChineseFromArabic("10"));
//        assertEquals("九十", numberFormatter.getChineseFromArabic("90"));
//        assertEquals("一百五十五", numberFormatter.getChineseFromArabic("155"));
//        assertEquals("一千二百三十四", numberFormatter.getChineseFromArabic("1234"));
//        assertEquals("一万一千五百五十一", numberFormatter.getChineseFromArabic("11551"));
//        assertEquals("二亿三千四百五十六万一千五百四十三", numberFormatter.getChineseFromArabic("234561543"));
//        assertEquals("二百万零一十", numberFormatter.getChineseFromArabic("2000010"));
//    }
}