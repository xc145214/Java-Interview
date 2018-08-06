package com.crossoverjie.design.pattern.observe;

/**
 * @author: xiac
 * @date: 2018/8/6
 * @desc: 主题接口
 */
public interface Subject {
    /**
     * 注册观察者
     *
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 删除观察者
     *
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 当主题状态发生改变时，这个方法需要被调用，以通知所有观察者
     */
    void notifyObserver();
}
