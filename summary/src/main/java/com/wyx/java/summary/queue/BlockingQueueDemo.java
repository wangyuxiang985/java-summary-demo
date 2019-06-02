package com.wyx.java.summary.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: yuxiang
 * @date: Create in 2019/6/1
 *
 * ArrayBlockingQueue:是一个基于数组结构的有界阻塞队列，此队列按FIFO(先进先出)原则对元素进行排序
 * LinkedBlockingQueue:是一个基于链表结构的阻塞队列，此队列按FIFO(先进先出)原则对元素进行排序，吞吐量通常要高于ArrayBlockingQueue
 * SynchronousQueue:是一个不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {

    }

    /**
     * 阻塞一定时间然后超时退出
     */
    private static void timesOut() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        try {
            blockingQueue.offer("a",2, TimeUnit.SECONDS);
            blockingQueue.offer("a",2, TimeUnit.SECONDS);
            blockingQueue.offer("a",2, TimeUnit.SECONDS);
            blockingQueue.offer("a",2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 满时一直阻塞
     */
    private static void blocks() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        try {
            blockingQueue.put("a");
            blockingQueue.put("a");
            blockingQueue.put("a");
            blockingQueue.put("a");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 特殊的添加移除方法，满时添加会返回false，空时取出会为null
     */
    private static void SpecialValue() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    /**
     * 阻塞队列普通add和remove方法，出现满或空时会抛异常
     */
    private static void throwsException() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }
}
