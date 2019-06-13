package com.wyx.java.summary.reference;

import java.lang.ref.SoftReference;

/**
 * @author: yuxiang
 * @date: Create in 2019/6/13
 * 软引用：内存够用不会回收，内存不够，进行回收
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {
        //softRef_Memory_Enough();
        softRef_Memory_NotEnouth();
    }

    /**
     * 内存足够用，软引用不会被回收
     */
    public static void softRef_Memory_Enough(){
        Object obj1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(obj1);
        System.out.println(obj1);
        System.out.println(softReference.get());

        obj1 = null;
        System.gc();
        System.out.println(obj1);
        System.out.println(softReference.get());
    }

    /**
     * 内存不够用时，软引用对象进行回收
     * 大对象配小内存，产生oom
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRef_Memory_NotEnouth(){
        Object obj1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(obj1);
        System.out.println(obj1);
        System.out.println(softReference.get());

        obj1 = null;

        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(obj1);
            System.out.println(softReference.get());
        }
    }

}
