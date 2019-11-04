package juc019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/*

使用CountDownLatch实现（最简单的方式）

Latch：门闩


使用Latch替代 wait notify来进行通信
好处是，通信简单，同时也可以指定等待时间
使用await和countDown 方法替代 wait 和 notify
CountDownLatch不涉及锁定，当count值为0时，当前线程继续运行
当不涉及同步，只涉及线程通信的时候，用synchronized + wait + notify 就显得太重了
 */
public class MyContainer4 {

    //写容器
    private List<Object> list = new ArrayList<>();

    public void add(Object o ) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer4 container = new MyContainer4();

        //CountDownLatch 往下数的门闩
        CountDownLatch latch = new CountDownLatch(1);


        //新线程获取size()
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"  启动了");
            if (container.size() != 5) {
                //latch.await();
                //也可以指定等待时间
                try {
                    latch.await(5000,TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"监测到容器长度为5，线程2立即退出");
        },  " 新线程t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        //新线程add()
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"  启动了");
            for(int i=0;i<10;i++) {
                container.add(new Object());
                System.out.println(Thread.currentThread().getName()+"   add"+i);
                if(container.size()==5) {
                    // 打开门闩，让t2线程执行

                    latch.countDown();//构造方法中的1countDown减1， 变成0，门开了
                }

            }
        }," 新线程t1" ).start();



    }


}


