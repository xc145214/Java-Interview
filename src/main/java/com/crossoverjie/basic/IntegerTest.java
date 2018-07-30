package com.crossoverjie.basic;

/**
 * @author: xiac
 * @date: 2018/7/30
 * @desc: 注意 包装类的缓存
 */
public class IntegerTest {

    public static void main(String[] args) {
        /**
         * -128~127之间有缓存
         */
        Integer a = 10;
        Integer b = 10;

        System.out.println("a==b : " + (a == b));

        Integer m = 1000;
        Integer n = 1000;

        System.out.println("m==n : " + (m == n));


    }
}
