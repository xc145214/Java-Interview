package com.crossoverjie.basic;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: xiac
 * @date: 2018/7/31
 * @desc: 一句话描述
 */
public class PlayQueueWithArrayListTest {

    PlayQueueWithArrayList playQueue;
    List<Person> normalList;
    List<Person> vipList;

    @Before
    public void setUp() throws Exception {

        normalList = new ArrayList<Person>();
        normalList.add(new Person("A", 40));
        normalList.add(new Person("B", 40));
        normalList.add(new Person("C", 40));
        normalList.add(new Person("D", 40));
        normalList.add(new Person("E", 40));

        vipList = new ArrayList<>();
        vipList.add(new Person("vipA", 400));
        vipList.add(new Person("vipB", 400));
        vipList.add(new Person("vipC", 400));
        vipList.add(new Person("vipD", 400));
        vipList.add(new Person("vipE", 400));

        playQueue = new PlayQueueWithArrayList(4);
    }

    @Test
    public void put() {
        for (Person person : normalList) {
            playQueue.put(person);
        }
       assertEquals(playQueue.queue.size(), normalList.size());
    }

    @Test
    public void putVip() {
        for (Person person : vipList) {
            playQueue.putVip(person);
        }
       assertEquals(playQueue.vipQueue.size(), vipList.size());
    }

    @Test
    public void take() {
        for (Person person : normalList) {
            playQueue.put(person);
        }

        for (Person person : vipList) {
            playQueue.putVip(person);
        }
        System.out.println("排队人数:" + playQueue.getQueSize());
        Person person = null;
        int length = playQueue.getQueSize();
        for (int i = 0; i <= length; i++) {
            person = playQueue.take();
            if (person == null) {
                System.out.println("已经没人玩了！");
                break;
            }
            if (person.isVip()) {
                System.out.println("VIP用户" + person.getName() + "开始玩！");
            } else {
                System.out.println("普通用户" + person.getName() + "开始玩！");
            }
        }

    }

    @Test
    public void takePlayList() {

        for (Person person : normalList) {
            playQueue.put(person);
        }

        for (Person person : vipList) {
            playQueue.putVip(person);
        }
        System.out.println("排队人数:" + playQueue.getQueSize());

        int batch = playQueue.getQueSize() / playQueue.getBatch() + 1;

        for (int i = 0; i <= batch; i++) {

            List<Person> personList = playQueue.takePlayList();
            if (personList.size() == 0) {
                System.out.println("没人玩小马车了！");
                return;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (Person person : personList) {
                stringBuilder.append(person.getName() + "-");
            }

            System.out.println(stringBuilder.toString() + "一起玩小马车！");

        }


    }
}