package com.crossoverjie.basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: xiac
 * @date: 2018/7/31
 * @desc: 使用LinkedList
 */
public class PlayQueueWithLinkedList {
    /**
     * 项目一次几人玩。
     */
    private int batch;

    private int queSize;

    /**
     * 普通队列
     */
    public LinkedList<Person> queue;

    /**
     * VIP队列
     */
    public LinkedList<Person> vipQueue;


    public PlayQueueWithLinkedList(int batch) {
        if (batch < 1) {
            throw new IllegalArgumentException("初始化参数错误batch不能小于1");
        }
        this.batch = batch;
        queue = new LinkedList<>();
        vipQueue = new LinkedList<Person>();
    }

    /**
     * 普通用户加入排队。
     *
     * @param player
     */
    public void put(Person player) {
        queue.add(player);
    }

    /**
     * VIP 用户加入排队。
     *
     * @param player
     */
    public void putVip(Person player) {
        vipQueue.add(player);
    }

    /**
     * 从队列中取出玩家。
     * 如果VIP队列里有人先取，否则取普通队列，如果都没人，返回空。
     *
     * @return
     */
    public Person take() {
        if (vipQueue.size() > 0) {
            return vipQueue.removeFirst();
        }
        if (queue.size() > 0) {
            return queue.removeFirst();
        }
        return null;
    }

    public List<Person> takePlayList() {
        List<Person> list = new ArrayList<>(this.batch);
        Person person = null;
        for (int i = 0; i < batch; i++) {
            //一个未取到直接返回
            person = this.take();
            if (person == null) {
                return list;
            }
            list.add(person);
        }
        return list;
    }

    public int getBatch() {
        return batch;
    }

    public int getQueSize() {
        return queue.size() + vipQueue.size();
    }
}
