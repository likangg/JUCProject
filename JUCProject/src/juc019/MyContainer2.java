package juc019;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到达5时，线程2给出提示并结束
 */
public class MyContainer2 {

    //写容器
    volatile List list = new ArrayList<>();

    public void add(Object o ) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer2 container = new MyContainer2();

        Thread.currentThread().setName("主线程");
        //新线程add()
        new Thread(()->{
            for(int i=0;i<10;i++) {
                container.add(new Object());
                System.out.println(Thread.currentThread().getName()+"   add"+i);

            }},"新线程t1" ).start();
        //新线程获取size()

        new Thread(() -> {
            while (true) {
                if (container.size() == 5) {
                    break;
                }
            }
            System.out.println(Thread.currentThread().getName()+"  监测到容器长度为5，线程2立即退出");
        }, "新线程t2").start();



    }



}

/* 添加 volatile 关键字，通知其他线程该数据发生变化
新线程t1add0
新线程t1add1
新线程t1add2
新线程t1add3
新线程t1add4
新线程t2监测到容器长度为5，线程2立即退出
新线程t1add5
新线程t1add6
新线程t1add7
新线程t1add8
新线程t1add9

*/
/*新问题：
添加 volatile ，使list发生变化时，主动通知其他线程，更新工作空间

上述代码，共有以下几个问题：
1. 不够精确，当container.size == 5 还未执行break时，有可能被其他线程抢占；或者 container.add() 之后，还未打印，就被 t2 判断size为5 直接推出了
2. 损耗性能，t2 线程，一直在走while循环，很浪费性能

 */
