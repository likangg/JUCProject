package juc004;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * synchronized
 * 同步代码块：细粒度  效率高
 * 同步方法：粗粒度
 */

public class TS12{

    int count = 0;

    //方法一:粗粒度锁：给整个方法加上锁
    public synchronized  void m1() {

        // 模拟其他业务代码，do sth need not synchronized
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //如果业务逻辑中只有下面这句需要synchronized，此时不应该给整个方法加上锁
        count++;

        //模拟 其他业务代码，do sth need not synchronized
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //方法二: 细粒度锁。只给需要加锁的一部分代码块
    public void m2() {

        // 模拟其他业务代码，do sth need not synchronized
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //需要加锁的代码块
        synchronized(this) {
            count++;
        }

        //模拟 其他业务代码，do sth need not synchronized
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        // 创建一个10个线程的list，执行任务皆是 m方法
        TS12 t = new TS12();
        List<Thread> threads = new ArrayList<>();



    }

}


