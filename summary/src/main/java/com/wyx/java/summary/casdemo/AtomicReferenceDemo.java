package com.wyx.java.summary.casdemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: yuxiang
 * @date: Create in 2019/5/14
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User z3 = new User("zhangsan", 22);
        User li4 = new User("lisi", 25);

        AtomicReference<User> atomicReference = new AtomicReference<>(z3);
        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t user:" + atomicReference.get());
        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t user:" + atomicReference.get());

    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class User{
    private String userName;
    private int age;
}