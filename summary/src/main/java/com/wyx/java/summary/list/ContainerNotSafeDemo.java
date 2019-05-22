package com.wyx.java.summary.list;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全问题
 * ArrayList:java.util.ConcurrentModificationException
 * @author: yuxiang
 * @date: Create in 2019/5/19
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        for(int i = 1; i<30; i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
        /**
         * 1、故障现象：
         * java.util.ConcurrentModificationException
         *
         * 2、导致原因：
         *
         * 3、解决方案：
         *  3.1：new Vector<>()
         *  3.2:Collections.synchronizedList(new ArrayList<>())
         *  3.3:new CopyOnWriteArrayList<>()
         * 4、优化建议：
         */
    }
}
