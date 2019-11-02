package juc004;

/*模拟线程的死锁
 * */
public class TS08 implements Runnable {

    public synchronized  void m1() {
        System.out.println(Thread.currentThread().getName()+":m1  start....=");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        m2();
        System.out.println(Thread.currentThread().getName()+":m1  end....=");
    }

    public synchronized  void m2() {
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

        TS08 ts = new TS08();
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
