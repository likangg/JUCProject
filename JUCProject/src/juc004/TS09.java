package juc004;

import java.util.concurrent.TimeUnit;

/*出异常，锁将被释放
 * */
public class TS09{
    int count = 0;

    public synchronized  void m1() {
        System.out.println(Thread.currentThread().getName()+": start....=");
        while(true) {
            count++;
            System.out.println(Thread.currentThread().getName()+": count="+count);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if(count == 5) {
                int i = 1/0; //此处抛出异常，锁将被释放
            }
        }
    }


    public static void main(String[] args) {

        TS09 t = new TS09();
        new Thread(() -> t.m1(),"t1").start();
        try {

            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        new Thread(() -> t.m1(),"t2").start();


    }







}
