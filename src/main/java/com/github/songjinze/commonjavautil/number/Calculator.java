package com.github.songjinze.commonjavautil.number;

public class Calculator {
    /**
     * 求最大公约数
     * 此方法使用辗转相除法
     * @param a 数字1
     * @param b 数字2
     * @return a, b的最大公约数
     */
    public static int getMaxCommonDivisor1(int a, int b){
        int temp = 1;
        int aa = Math.max(a,b);
        int bb = Math.min(a,b);
        while(temp != 0){
            temp = aa % bb;
            aa = bb;
            bb = temp;
        }
        return aa;
    }

    /**
     * 求最大公约数
     * 此方法使用更相减损法
     * @param a 数字1
     * @param b 数字2
     * @return a, b最大公约数
     */
    public static int getMaxCommonDivisor2(int a, int b){
        int m = 0xfffffffe;
        int bCount = 0;
        while((m & a) == 0 && (m & b) == 0) {
            a = a >> 1;
            b = b >> 1;
            bCount++;
        }
        while(true){
            if(a > b){
                a = a - b;
            }else if(a < b){
                b = b - a;
            }else{
                break;
            }
        }
        return a << bCount;
    }

    public static long getMinCommonMultiple(int a, int b){
        return ((long) a * (long) b) / getMaxCommonDivisor1(a, b);
    }


}
