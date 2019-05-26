package com.wyx.java.summary.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多个线程同时读一个资源类没有问题，所以为了满足并发量，读取共享资源应该可以同时进行；
 * 但是如果有一个线程想去写共享资源，就不应该再有其他线程可以对资源类进行读或写。
 * 读：读能共享
 * 读：写不能共享
 * 写：写不能共享
 * 写操作：原子+独占，整个过程必须是一个完整的统一体，中间不能被加塞
 * @author: yuxiang
 * @date: Create in 2019/5/26
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCatch myCatch = new MyCatch();

        for(int i = 1; i<5; i++){
            final int tempInt = i;
            new Thread(() -> {
                myCatch.put(tempInt + "",tempInt + "");
            },String.valueOf(i)).start();
        }
        for(int i = 1; i<5; i++){
            final int tempInt = i;
            new Thread(() -> {
                myCatch.get(tempInt + "");
            },String.valueOf(i)).start();
        }
    }
}

class MyCatch{
    private Map<String, Object> map = new HashMap<>();
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    //写操作
    public void put(String key , Object object){
        lock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t 正在写入 key:"+ key);
            //暂停一会儿线程，模拟网路
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, object);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.writeLock().unlock();
        }

    }

    //读操作
    public void get(String key ){
        lock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t 开始读取");
            //暂停一会儿线程，模拟网路
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成：result:" + o);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.readLock().unlock();
        }

    }
}
