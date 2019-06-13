package com.wyx.java.summary.reference;

import java.lang.ref.WeakReference;

/**
 * @author: yuxiang
 * @date: Create in 2019/6/13
 * 弱引用：不管内存够用与否，只要发生了gc就会被垃圾回收器进行回收
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(obj1);
        System.out.println(obj1);
        System.out.println(weakReference.get());

        obj1 = null;
        System.gc();
        System.out.println("##########发生GC后############");
        System.out.println(obj1);
        System.out.println(weakReference.get());
    }
}
