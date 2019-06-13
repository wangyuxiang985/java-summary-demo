package com.wyx.java.summary.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author: yuxiang
 * @date: Create in 2019/6/13
 * java提供了四种引用类型，在垃圾回收的时候都有各自的特点
 * ReferenceQueue是配合工作的，没有它也一样的工作
 * 创建引用的时候可以指定关联的队列，当GC释放对象内存的时候，会将引用加入到引用队列，
 * 如果程序发现某个虚拟引用已经加入到引用队列，那么就可以在所引用的对象内存被回收之前采取必要行动，相当于一种通知机制。
 * 当关联的引用队列有数据时，意味着引用指向的对内存中的对象被回收，通过这种机制，JVM允许我们在对象被销毁后，做一些我们想做的事情
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(obj1, referenceQueue);
        System.out.println(obj1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        obj1 = null;
        System.gc();
        System.out.println("#########GC############");

        System.out.println(obj1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

    }
}
