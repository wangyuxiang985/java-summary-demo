package com.wyx.java.summary.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁：
 同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，在同一个线程外层方法获取锁的时候，在进入内层方法会自动获取锁。
 即：线程可以进入任何一个它已经拥有的锁同步着的代码块。
 * @author: yuxiang
 * @date: Create in 2019/5/20
 */
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendSMS();
        },"t1").start();
        new Thread(() -> {
            phone.sendSMS();
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("##########################");

        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");
        t3.start();
        t4.start();
    }
}

class Phone implements Runnable{
    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName() + "\t invoke sendSMS");
        sendEmail();
    }
    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName() + "\t ########invoke sendEmail");
    }


    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }
    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoke get");
            set();
        }finally {
            lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t ########invoke set");
        }finally {
            lock.unlock();
        }
    }
}