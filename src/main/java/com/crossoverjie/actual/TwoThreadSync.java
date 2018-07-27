package com.crossoverjie.actual;

/**
 * Function: 两个线程交替执行打印 1~100
 *
 * Synchronize 版
 *
 * @author crossoverJie
 *         Date: 11/02/2018 10:04
 * @since JDK 1.8
 */
public class TwoThreadSync {

    private int start = 1;

    /**
     * 保证内存可见性
     * 其实用锁了之后也可以保证可见性 这里用不用 volatile 都一样
     */
    private boolean flag = false;



    public static void main(String[] args) {
        TwoThreadSync twoThread = new TwoThreadSync();

        Thread t1 = new Thread(new OuNum(twoThread));
        t1.setName("t1");


        Thread t2 = new Thread(new JiNum(twoThread));
        t2.setName("t2");

        t1.start();
        t2.start();
    }

    /**
     * 偶数线程
     */
    public static class OuNum implements Runnable {

        private TwoThreadSync number;

        public OuNum(TwoThreadSync number) {
            this.number = number;
        }

        @Override
        public void run() {
            while (number.start <= 100) {

                if (number.flag) {

                    synchronized (number) {
                        System.out.println(Thread.currentThread().getName() + "+-+" + number.start);
                        number.start++;
                        number.flag = false;
                    }


                } else {
                    try {
                        //防止线程空转
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 奇数线程
     */
    public static class JiNum implements Runnable {

        private TwoThreadSync number;

        public JiNum(TwoThreadSync number) {
            this.number = number;
        }

        @Override
        public void run() {
            while (number.start <= 100) {

                if (!number.flag) {

                    synchronized (number) {
                        System.out.println(Thread.currentThread().getName() + "+-+" + number.start);
                        number.start++;
                        number.flag = true;
                    }

                } else {
                    try {
                        //防止线程空转
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
