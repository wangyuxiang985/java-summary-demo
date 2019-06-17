package com.wyx.java.summary.oom;

/**
 * @author: yuxiang
 * @date: Create in 2019/6/17
 */
public class OOM_JavaHeapSpacedDemo {
    public static void main(String[] args) {
        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        Byte[] bytes = new Byte[80 * 1024 * 1024];
    }
}
