package com.wyx.java.summary.volatiledemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1、验证volatile的可见性
 * 例如：1.1 int number = 0;number变量之前根本没有后volatile关键字修饰，没有可见性
 *      1.2 添加了volatile关键字，解决了可见性问题
 * 2、验证volatile不保证原子性
 *      2.1原子性：不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或分割，需要整体完整；同时成功或同时失败
 *      2.2 volatile 不保证原子性
 *      2.3解决原子性问题
 *         加synchronized；
 *         使用JUC下的AtomicInteger
 *
 * @author: yuxiang
 * @date: Create in 2019/4/28
 */
public class VolatileDemo {

    public static void main(String[] args) {

        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            },String.valueOf(i)).start();
        }

        //需要等到上面20个线程执行结束，main线程取得结果值
        while (Thread.activeCount() >2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t int type, finally number value :" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type, finally number value :" + myData.atomicInteger);


    }

    //volatile 可以保证内存可见性，及时通知其他线程，主物理内存线程已经被修改
    private static void seeOkByVolatile() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t update number value:"+myData.number);
        },"AAA").start();

        //main线程
        while (myData.number == 0){
            //main线程一直等待循环直到number不等于0
        }
        System.out.println(Thread.currentThread().getName()+"\t mission over,main number value:"+myData.number);
    }

}
class MyData{
    volatile int number = 0;
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addTo60(){
        this.number = 60;
    }
    public void addPlusPlus(){
        number++;
    }
    public void addMyAtomic(){
        atomicInteger.getAndIncrement();
    }


}
