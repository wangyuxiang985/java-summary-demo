package com.wyx.java.summary.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: yuxiang
 * @date: Create in 2019/6/2
 * 传统版生产者消费者模式
 * 目的：一个变量0，两个线程，一个加1，一个减1，循环5次；
 *
 * 多线程规范：
 * 线程 操作 资源类
 * 判断 干活 通知
 * 防止虚假唤醒 要循环判断用while
 *
 */
public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }

}

class ShareData{
    private int num = 0 ;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception{
        lock.lock();
        try{
            while (num != 0){
                //线程等待
                condition.await();
            }
            //干活
            num++;
            System.out.println(Thread.currentThread().getName() + "\t num=" + num);
            //通知
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void decrement() throws Exception{
        lock.lock();
        try{
            while (num == 0){
                //线程等待
                condition.await();
            }
            //干活
            num--;
            System.out.println(Thread.currentThread().getName() + "\t num=" + num);
            //通知
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}