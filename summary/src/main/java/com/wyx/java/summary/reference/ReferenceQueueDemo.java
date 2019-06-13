package com.wyx.java.summary.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author: yuxiang
 * @date: Create in 2019/6/13
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<Object>(obj1, referenceQueue);
        System.out.println(obj1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        obj1 = null;
        System.gc();
        System.out.println("#########GC##########");

        System.out.println(obj1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

    }
}
