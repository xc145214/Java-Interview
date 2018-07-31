package com.crossoverjie.basic;

/**
 * @author: xiac
 * @date: 2018/7/31
 * @desc: 一句话描述
 */
public class Person {
    private String name;

    private int price;
    private boolean isVip = false;

    public Person() {
    }

    public Person(String name, int price) {
        this.name = name;
        this.price = price;
        if (price > 100) {
            this.setVip(Boolean.TRUE);
        }
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", isVip=" + isVip +
                '}';
    }
}
