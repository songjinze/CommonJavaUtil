package com.github.songjinze.commonjavautil.number;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    private int[][] commonDivisorTable = {
            {319, 377, 29},
            {2, 3, 1},
            {98, 63, 7}
    };

    private int[][] commonMultipleTable = {
            {45, 30 ,90},
            {36, 270, 540},
            {20, 40, 40}
    };

    @Test
    public void getMaxCommonDivisor1() {
        for(int[] a : commonDivisorTable){
            assertEquals(a[2], Calculator.getMaxCommonDivisor1(a[0],a[1]));
        }
    }

    @Test
    public void getMaxCommonDivisor2() {
        for(int[] a : commonDivisorTable){
            assertEquals(a[2], Calculator.getMaxCommonDivisor2(a[0],a[1]));
        }
    }

    @Test
    public void getMinCommonMultiple(){
        for(int[] a : commonMultipleTable){
            assertEquals(a[2], Calculator.getMinCommonMultiple(a[0], a[1]));
        }
    }
}