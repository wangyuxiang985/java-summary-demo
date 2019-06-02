package com.wyx.java.summary.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: yuxiang
 * @date: Create in 2019/6/2
 * 问题：synchronized和Lock有什么区别，用Lock有什么好处
 * 1、原始构成：
 *  synchronized是关键字，属于JVM层面，
 *      monitorenter(底层是通过monitor对象来完成，其实wait与notify方法也依赖于monitor对象，只有在同步方法或同步代码块中才能调用wait/notify方法)
 *      monitorexit
 *  Lock是具体类（java.unit.concurrent.locks.lock）是api层面
 * 2、使用方法
 *  synchronized 不需要用户去手动释放锁，当synchronized代码执行完后系统会自动让线程释放对锁的占用；
 *  ReentrantLock 则需用户手动释放锁，若没有释放锁，有可能导致死锁现象发生，需要lock()和unlock()方法配合try/finally代码块执行；
 * 3、等待是否中断
 *  synchronized不可中断，除非抛出异常或者程序运行完
 *  ReentrantLock 可中断，
 *      1）设置超时方法，tryLock(long time , TimeUnit unit);
 *      2)lockInterruptibly()放代码块中，调用interrupt()方法可中断。
 * 4、加锁是否公平
 *  synchronized非公平锁；
 *  ReentrantLock 两者都可以，默认非公平锁，构造方法可传入boolean值，true为公平锁，false为非公平锁。
 * 5、锁绑定多个条件Condition
 *  synchronized没有
 *  ReentrantLock用来实现分组唤醒需要唤醒的线程们，可以精确唤醒，而不是像synchronized要么随机唤醒一个线程，要么全部唤醒
 *
 *题目：
 * 多线程之间按顺序调用，A->B->C三个线程启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次，循环10次
 *
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <=10; i++) {
                shareResource.print5();
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 1; i <=10; i++) {
                shareResource.print10();
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 1; i <=10; i++) {
                shareResource.print15();
            }
        },"C").start();
    }
}

class ShareResource{
    //标志位 1：A线程；2：B线程；3：C线程
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try{
            while (num != 1){
                c1.await();
            }
            for (int i = 1; i <=5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            num = 2;
            c2.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try{
            while (num != 2){
                c2.await( );
            }
            for (int i = 1; i <=10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            num = 3;
            c3.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try{
            while (num != 3){
                c3.await();
            }
            for (int i = 1; i <=15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            num = 1;
            c1.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

}

