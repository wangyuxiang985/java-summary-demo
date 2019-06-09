package com.wyx.java.summary.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author: yuxiang
 * @date: Create in 2019/6/9
 * 死锁是指两个或两个以上的线程在执行过程中，因争夺资源而造成的一种互相等待现象
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA,lockB),"ThreadAAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"ThreadBBB").start();
    }
}

class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA,String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t 持有锁 ：" + lockA + "\t 尝试获取锁：" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t 持有锁：" + lockB + "\t 尝试获取锁：" + lockB);
            }
        }
    }
}
