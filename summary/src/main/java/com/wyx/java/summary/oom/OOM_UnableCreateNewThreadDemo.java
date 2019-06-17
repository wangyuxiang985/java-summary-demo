package com.wyx.java.summary.oom;

/**
 * @author: yuxiang
 * @date: Create in 2019/6/17
 * 高并发请求服务时，经常出现如下异常：java.lang.OutOfMemoryError:unable to creat new native thread
准确的讲该native thread异常与对应的平台有关。

导致原因：
1）应用创建了太多的线程，一个应用进行创建多个线程，超过系统承载极限；
2）服务器并不允许应用程序创建这么多线程，Linux系统默认允许单个进程可以创建的线程数为1024个，如果应用程序创建的线程超过这个数量，就会报：java.lang.OutOfMemoryError:unable to creat new native thread。

解决办法：
1）想办法降低应用程序创建线程数量，分析应用是否真的需要创建这么多线程，如果不是，则该代码，将线程数量降至最低。
2）对于有的应用，确实需要创建很多线程，远超过Linux系统默认的1024个线程限制，可以通过修改Linux服务器配置，扩大linux默认限制
 */
public class OOM_UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            System.out.println("*************i：" + i);

                new Thread(() ->{
                    try {
                        Thread.sleep(Integer.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },i + "").start();

        }
    }
}
