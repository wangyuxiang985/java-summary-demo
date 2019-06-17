package com.wyx.java.summary.oom;

import java.nio.ByteBuffer;

import static sun.misc.VM.maxDirectMemory;

/**
 * @author: yuxiang
 * @date: Create in 2019/6/17
 * 参数配置：
-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m

故障现象：
Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
导致原因：
写NIO程序时，经常使用ByteBuffer来读取或写入数据，这是一种基于通道（Channel）与缓存区（Buffer）的I/O方式，
它可以使用native函数库直接分配堆外内存，然后通过一个存储在Java堆里面的DirectByteBuffer对象作为这块内存的引用进行操作。
这样能在一些场景中 显著提高性能，因为避免了在Java堆和native堆之间来回复制数据。

ByteBuffer.allocate(capability)：第一种方式是分配JVM内存，属于GC管理范畴，由于需要内存拷贝，所以速度相对较慢；
ByteBuffer.allocateDirect(capability)：第一种方式是分配OS本地内存，不属于GC管理范畴，由于不需要内存拷贝，所以速度相对较快。

但是如果不断分配本地内存，堆内存很少使用，那么JVM就不需要执行GC，DireceByteBuffer对象就不会被回收，这时候堆内存充足，但本地内存可能已经使用光了，再次尝试分配本地内存就会出现OutOfMemoryError,那程序就直接崩溃了。
 */
public class OOM_DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory:" + maxDirectMemory()/(double)1024/1024 +"MB");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
