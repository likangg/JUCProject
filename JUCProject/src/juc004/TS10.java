package juc004;

import java.util.concurrent.TimeUnit;

/**
 * volatile 关键字，使一个变量在多个线程间可见
 * cn: 透明的，临时的
 *
 * JMM(Java Memory Model)：
 * 在JMM中，所有对象以及信息都存放在主内存中（包含堆、栈）
 * 而每个线程都有自己的独立空间，存储了需要用到的变量的副本，
 * 线程对共享变量的操作，都会在自己的工作内存中进行，然后同步给主内存
 *
 * 下面的代码中，running 是位于堆内存中的t对象的
 * 当线程t1开始运行的时候，会把running值从内存中读到t1线程的工作区，在运行过程中直接使用这个copy，并不会每次都会去读取堆内存，
 * 这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止运行
 *
 * 使用volatile，将会强制所有线程都去堆内存中读取running的值
 * 当共享数据发生改变时，volatile会通知各线程此数据发生变化，重新加载
 * */
public class TS10{

    /* volatile */ boolean running = true;

    public void m1() {
        System.out.println(Thread.currentThread().getName()+"m  start....=");
        while(running) {
            try {
                TimeUnit.MICROSECONDS.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+": start....=");
        }
        System.out.println(Thread.currentThread().getName()+":m  end....=");
    }


    public static void main(String[] args) {

        TS10 t = new TS10();
        new Thread(() -> t.m1(),"t1").start();
        try {

            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        t.running=false;


    }







}
