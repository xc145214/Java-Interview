package com.crossoverjie.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: xiac
 * @date: 2018/7/27
 * @desc: 一句话描述
 */
public class SortUtilTest {


    int[] numbers = new int[]{
        1,2,3,4,6,5,3,4,234,634,6,43
    };

    @Test
    public void sort1() {
        printArray(numbers);
        SortUtil.bubbleSort(numbers);
        printArray(numbers);
    }

    @Test
    public void sort2() {
        printArray(numbers);
        SortUtil.insertSort(numbers);
        printArray(numbers);
    }

    @Test
    public void sheelSort() {
        printArray(numbers);
        SortUtil.sheelSort(numbers);
        printArray(numbers);
    }

    @Test
    public void selectSort() {
        printArray(numbers);
        SortUtil.selectSort(numbers);
        printArray(numbers);
    }

    private void printArray(int[] arr){
        System.out.println("--------------");
        for (int i :arr){
            System.out.print( i +" , ");
        }
        System.out.println();
        System.out.println("--------------");
    }
}