package com.wyx.java.summary.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: yuxiang
 * @date: Create in 2019/6/3
 * 第四种获取线程，开启多线程----线程池
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //查看电脑cpu数量
        //System.out.println(Runtime.getRuntime().availableProcessors());
        //一池五个线程
        //ExecutorService executorService =
        //一池一线程
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        //一池N线程
        ExecutorService executorService = Executors.newCachedThreadPool();

        try{
            for (int i = 1; i <= 10; i++) {//模拟十个请求
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 线程执行");
                });
                TimeUnit.MILLISECONDS.sleep(200);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            executorService.shutdown();
        }
    }
}
