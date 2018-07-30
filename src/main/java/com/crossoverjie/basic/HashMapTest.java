package com.crossoverjie.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Function:   HashMap的多种遍历方式。
 *
 * @author crossoverJie
 * Date: 05/05/2018 12:42
 * @since JDK 1.8
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(16);
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);

        /**
         * 二次遍历法。
         */
        System.out.println("=======二次遍历法========");
        for (String key : map.keySet()) {
            System.out.println("key=" + key + " value=" + map.get(key));
        }

        /**
         * 使用迭代器迭代 entry。
         */
        System.out.println("======使用迭代器迭代 entry=======");
        Iterator<Map.Entry<String, Integer>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Integer> next = entryIterator.next();
            System.out.println("key=" + next.getKey() + " value=" + next.getValue());
        }

        /**
         * 使用迭代器迭代 key。
         */
        System.out.println("======使用迭代器迭代 key=======");
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println("key=" + key + " value=" + map.get(key));

        }

        /**
         * 使用map.entrySet()方法。
         */
        System.out.println("======使用map.entrySet()方法=======");
        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("key=" + entry.getKey() + " value=" + entry.getValue());
        }

        /**
         * 使用lambda表达式。
         */
        System.out.println("=======使用lambda表达式=======");
        map.forEach((key, value) ->
                System.out.println("key=" + key + " value=" + map.get(key))
        );
    }
}
