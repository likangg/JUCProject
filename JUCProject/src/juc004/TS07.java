package juc004;

/*一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请时仍然会得到该对象的锁
 * 也就是synchronized获得的锁是可重入的，获得锁还可以加锁
 *但是注意前提：同一个线程里面
 *不同线程是不可以的
 *synchronized 锁的是线程
 * */
public class TS07 implements Runnable {

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

		TS07 ts = new TS07();
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
