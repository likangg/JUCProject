package juc004;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 *  * 锁定某个对象o，如果o属性发生变化，不影响锁的使用
 * 但是如果o编程另一个对象，则锁定的对象发生变化，
 * 所以锁对象通常要设置为 final类型，保证引用不可以变
 */

public class TS13{

    Object o = new Object();

    void m() {
        synchronized (o) {
            while (true) {
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        TS13 t = new TS13();
        new Thread(()->t.m(), "线程1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(()->t.m(), "线程2");
        // 改变锁引用, 线程2也有机会运行，否则一直都是线程1 运行，
        t.o = new Object();
        //所以：锁是锁的堆内存的对象，而不是该对象的引用
        thread2.start();
    }

}


