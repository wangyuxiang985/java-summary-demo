package com.wyx.java.summary.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yuxiang
 * @date: Create in 2019/6/17
 * JVM参数演示配置：
-Xms10m -Xmx10m -XX:MaxDirectMemorySize=5m

GC回收时间过长会抛出OutOfMemoryError，过长的定义：超多98%的时间用来做GC并且回收了不到2%的堆内存，连续多次GC，都只回收了不到2%的极端情况才会抛出。
假如不抛出GC overhead limit错误会发生什么情况呢？
那就是GC清理的那么点儿内存很快会再次填满，迫使GC再次执行，这样就造成恶性循环，CPU使用率一直是100%，而GC却没有任何成果。
 */
public class OOM_GCOverheadDemo {
    public static void main(String[] args) {
        int i = 0 ;
        List<String> list = new ArrayList<>();

        try {
            //Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
            while (true){
                list.add(String.valueOf(i++).intern());
            }
        } catch (Throwable e) {
            //System.out.println("*****************i:" + i);
            e.printStackTrace();
            throw e;
        }
    }
}
