package com.crossoverjie.design.pattern.observe;

/**
 * @author: xiac
 * @date: 2018/8/6
 * @desc: 观察者接口
 */
public interface Observer {

    void update(float temp, float humidity, float pressure);
}
