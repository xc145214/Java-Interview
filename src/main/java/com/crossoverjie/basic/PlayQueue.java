package com.crossoverjie.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiac
 * @date: 2018/7/31
 * @func: 游乐厂排队。
 */
public class PlayQueue {

    /**
     * 项目一次几人玩。
     */
    private int batch;

    private int queSize;

    /**
     * 普通队列
     */
    public List<Person> queue;

    /**
     * VIP队列
     */
    public List<Person> vipQueue;


    public PlayQueue(int batch) {
        if (batch < 1) {
            throw new IllegalArgumentException("初始化参数错误batch不能小于1");
        }
        this.batch = batch;
        queue = new ArrayList<Person>();
        vipQueue = new ArrayList<Person>();
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
            return vipQueue.remove(0);
        }
        if (queue.size() > 0) {
            return queue.remove(0);
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
                return list ;
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
