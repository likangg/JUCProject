package juc004;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * synchronized 同步代码块中，代码越少越好
 */

public class TS11{

    volatile int count = 0;
    //  AtomicInteger count = new AtomicInteger(0);

    /* synchronized */ void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
            // if( count.get() < 10000) count.incrementAndGet();

        }
    }

    public static void main(String[] args) {
        // 创建一个10个线程的list，执行任务皆是 m方法
        TS11 t = new TS11();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(()-> t.m(), "t-" + i));
        }

        // 启动这10个线程
        threads.forEach((o) -> o.start());

        // join 到主线程，防止主线程先行结束
        threads.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }});

        // 10个线程，每个线程执行10000次，结果应为 100000
        System.out.println(t.count);  // 所得结果并不为 100000，说明volatile 不保证原子性
    }

}


/*

解决方案：
1. 在方法上加上synchronized即可，synchronized既保证可见性，又保证原子性
生产中如果只涉及到++和--，涉及到效率的问题，可以用方法二解决：AtomicInteger原子性的改变 ++/--
2. 使用AtomicInteger代替int（AtomicXXX 代表此类中的所有方法都是原子操作，并且可以保证可见性）

 */
