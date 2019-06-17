package com.wyx.java.summary.oom;

/**
 * @author: yuxiang
 * @date: Create in 2019/6/17
 */
public class stackOverflowErrorDemo {
    public static void main(String[] args) {
        stackOverflowError();
    }

    //Exception in thread "main" java.lang.StackOverflowError
    private static void stackOverflowError() {
        stackOverflowError();
    }
}
