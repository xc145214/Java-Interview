package com.crossoverjie.basic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: xiac
 * @date: 2018/7/30
 * @desc: ArrayList 遍历方式
 */
public class ArrayListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("Hello");
        list.add("World");
        list.add("HAHAHAHA");
        //第一种遍历方法使用foreach遍历List
        for (String str : list) {            //也可以改写for(int i=0;i<list.size();i++)这种形式
            System.out.println(str);
        }

        //第二种遍历，把链表变为数组相关的内容进行遍历
        String[] strArray = new String[list.size()];
        list.toArray(strArray);
        //这里也可以改写为  foreach(String str:strArray)这种形式
        for (int i = 0; i < strArray.length; i++) {
            System.out.println(strArray[i]);
        }

        //第三种遍历 使用迭代器进行相关遍历
        Iterator<String> ite = list.iterator();
        //判断下一个元素之后有值
        while (ite.hasNext()) {
            System.out.println(ite.next());
        }
    }
}
