package com.wyx.java.summary.queue;

import org.springframework.util.StringUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yuxiang
 * @date: Create in 2019/6/2
 */
public class ProdConsumer_BlockingQueueDemo {

    public static void main(String[] args) {
        MyData myData = new MyData(new ArrayBlockingQueue<String>(10));
        new Thread(() -> {
            System.out.println("生产者线程启动");
            System.out.println();
            try {
                myData.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(() -> {
            System.out.println("消费者线程启动");
            System.out.println();
            try {
                myData.myCustomer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Customer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("5秒钟过去了，main线程终止了程序，flag = false");
        System.out.println();
        try {
            myData.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyData{
    //标志开关 ；true：线程开始生产消费；false：线程停止消费
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    public MyData(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    public void myProd() throws Exception{
        String data = null;
        boolean offer;
        while (flag){
            //开关开启，线程工作
            data = atomicInteger.incrementAndGet() + "";
            offer = blockingQueue.offer(data,2, TimeUnit.SECONDS);

            if(offer){
                System.out.println(Thread.currentThread().getName() + "\t 插入队列："+data+"成功");
            }else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列："+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 叫停了程序，flag=false，停止生产");

    }

    public void myCustomer() throws Exception{
        String data = null;
        while (flag){
            data = blockingQueue.poll(2, TimeUnit.SECONDS);
            if(data == null || data.equalsIgnoreCase("")){
                flag = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2秒，取出："+data+"失败");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 取出："+data+"成功");
        }

        //System.out.println(Thread.currentThread().getName() + "\t 叫停了程序，flag=false，停止消费");

    }
    public void stop()throws Exception{
        this.flag = false;
    }
}
