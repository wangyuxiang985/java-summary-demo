package com.wyx.java.summary.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author: yuxiang
 * @date: Create in 2019/6/3
 * 第三种实现多线程方法 实现Callable接口
 * 1、继承Thread类；
 * 2、实现Runable接口；
 * 3、实现Callable接口；
 * 4、线程池
 */
public class CallableDemo {
    public static void main(String[] args) throws Exception{
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread());
        new Thread(futureTask).start();

        System.out.println(Thread.currentThread().getName() + "\t &&&&&&&&&&&&");
        int rea1 = 100;
        int rea2 = futureTask.get();
        System.out.println("***********result:" + "\t" + rea1 + rea2);

    }
}

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t come in Callable");
        TimeUnit.SECONDS.sleep(3);
        return 1024;
    }
}
