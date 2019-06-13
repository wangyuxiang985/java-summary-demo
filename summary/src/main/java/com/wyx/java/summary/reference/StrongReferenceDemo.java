package com.wyx.java.summary.reference;

/**
 * @author: yuxiang
 * @date: Create in 2019/6/13
 * 强引用，引用指向对象不会被垃圾回收
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = obj1;
        obj1 = null;
        System.gc();
        System.out.println("obj1：" + obj1 + "\t obj2：" + obj2);
    }
}
