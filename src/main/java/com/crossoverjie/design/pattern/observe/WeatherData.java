package com.crossoverjie.design.pattern.observe;

import java.util.List;
import java.util.Vector;

/**
 * @author: xiac
 * @date: 2018/8/6
 * @desc: 一句话描述
 */
public class WeatherData implements Subject {

    private Vector<Observer> observers;
    private float tempterature;
    private float pressure;
    private float humidity;

    public WeatherData() {
        observers = new Vector<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);

    }

    @Override
    public void removeObserver(Observer observer) {
            observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = observers.get(i);
            observer.update(tempterature, humidity, pressure);
        }
    }



    /**
     * 气象站得到更新的观测数据时，通知观察者
     */
    public void measurementChanged(){
        notifyObserver();
    }

    public void setMeasurements(float temperature,float humidity,float pressure){
        this.tempterature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementChanged();
    }

}
