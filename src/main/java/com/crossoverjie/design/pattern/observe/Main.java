package com.crossoverjie.design.pattern.observe;

/**
 * @author: xiac
 * @date: 2018/8/6
 * @desc: 一句话描述
 */
public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionDisplay conditionsDisplay = new CurrentConditionDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 78, 40.4f);
    }

}
