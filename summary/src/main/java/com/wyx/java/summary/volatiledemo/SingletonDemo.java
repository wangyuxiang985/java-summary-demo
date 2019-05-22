package com.wyx.java.summary.volatiledemo;

/**
 * @author: yuxiang
 * @date: Create in 2019/5/13
 */
public class SingletonDemo {
    private static volatile SingletonDemo singletonDemo = null;
    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造函数SingletonDemo()");
    }

    //DCL模式（double check lock 双端检锁机制）
    public static SingletonDemo getSingletonDemo() {
        if(singletonDemo == null){
            synchronized (SingletonDemo.class){
                if(singletonDemo == null){
                    singletonDemo = new SingletonDemo();
                }
            }

        }
        return singletonDemo;
    }

    public static void main(String[] args) {
        //单线程
//        System.out.println(SingletonDemo.getSingletonDemo() == SingletonDemo.getSingletonDemo());
//        System.out.println(SingletonDemo.getSingletonDemo() == SingletonDemo.getSingletonDemo());
//        System.out.println(SingletonDemo.getSingletonDemo() == SingletonDemo.getSingletonDemo());
        System.out.println("#####################");
        //多线程
        for (int i = 0; i <= 10; i++) {
            new Thread(() -> {
                SingletonDemo.getSingletonDemo();
            },String.valueOf(i)).start();

        }

    }
}
