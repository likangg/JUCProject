package juc019;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到达5时，线程2给出提示并结束
 */
public class MyContainer1 {

    //写容器
    List list = new ArrayList<>();

    public void add(Object o ) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer1 container = new MyContainer1();

        Thread.currentThread().setName("主线程");
        //新线程add()
        new Thread(()->{
            for(int i=0;i<10;i++) {
                container.add(new Object());
                System.out.println(Thread.currentThread().getName()+"   add"+i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
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


/*

此种方法是一种错误的实现：
新线程t1   add0
新线程t1   add1
新线程t1   add2
新线程t1   add3
新线程t1   add4
新线程t1   add5
新线程t1   add6
新线程t1   add7
新线程t1   add8
新线程t1   add9
.... t2 一直在运行，永远不结束

这是因为 container 对象的可见性问题

 */


