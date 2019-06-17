package com.wyx.java.summary.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: yuxiang
 * @date: Create in 2019/6/17
 * JVM演示配置：
-XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m

不断生成类往元空间灌，类占据的空间总是会超过metaspace制定的大小

java8之后Metaspace替代了永久代
Metaspace是方法去在HotSpot中的实现，它与持久代最大的区别在于：Metaspace并不在虚拟机内存中，而是在本地内存，也即在Java8中，classe metadata(the virtaul machines internal presentation of java class ),被存储在叫做Metaspace的native memory。
java.lang.OutOfMemoryError: Metaspace
永久代存放一下信息：
1）虚拟机加载的类信息；
2）常量池；
3）静态变量；
4）即时编译后的代码
 */
public class OOM_MetaspaceDemo {
    public static void main(String[] args) {
        //模拟计数，多少次后发生元空间异常
        int i = 0;
        try {
            while (true){
                 i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOM_MetaspaceDemo.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invoke(o,args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable throwable) {
            System.out.println("多少次后发生异常i：" + i);
            throwable.printStackTrace();
        }
    }
}
