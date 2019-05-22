package com.wyx.java.summary.casdemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yuxiang
 * @date: Create in 2019/5/13
 * CAS是什么？ ===》compareAndSet
 * 1、比较并交换
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t atomicInteger:"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024)+"\t atomicInteger:"+atomicInteger.get());
        atomicInteger.getAndIncrement();
    }


}
