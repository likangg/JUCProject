package juc002;

//同步方法和非同步方式是否可以同时调用
public class TS06 implements Runnable {

    public synchronized  void m1() {
        System.out.println(Thread.currentThread().getName()+":m1  start....=");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":m1  end....=");
    }

    public  void m2() {
        System.out.println(Thread.currentThread().getName()+":m2 start....=");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":m2 end....=");
    }

    public static void main(String[] args) {

        TS06 ts = new TS06();
        new Thread(() -> ts.m1(),"t1").start();
        new Thread(() -> ts.m2(),"t2").start();

		/*
		new Thread(ts :: m1,"t1").start();
		new Thread(ts :: m2,"t2").start();

		*/

    }


    @Override
    public void run() {
        // TODO Auto-generated method stub

    }





}
