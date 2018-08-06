package com.crossoverjie.synchronize;

/**
 * Function:Synchronize 演示
 *
 * @author crossoverJie
 * Date: 02/01/2018 13:27
 * @since JDK 1.8
 */
public class Synchronize {

    /**
     * 同步静态方法。
     */
    public static synchronized void syncStaticMethod() {
        System.out.println("Synchronize static method");
    }


    /**
     * 同步普通方法
     */
    public synchronized void syncMethod() {
        System.out.println("Synchronize method");
    }

    /**
     * 同步对象。
     */
    public void syscObject() {
        synchronized (this) {
            System.out.println("Synchronize object");
        }
    }

    /**
     * 同步类。
     */
    public void syncClass() {
        synchronized (Synchronize.class) {
            System.out.println("Synchronize class");
        }
    }
}
