package com.wyx.java.summary.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author: yuxiang
 * @date: Create in 2019/5/27
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for(int i = 1; i<=6; i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 国被灭");
                countDownLatch.countDown();
            },CountryEnum.foreach_countryEnum(i).getMessage()).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t ***********秦帝国，一统华夏");

    }

    private static void closeDoor() {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for(int i = 1; i<=6; i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t 离开教室，并锁门");
    }

}
