package juc020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可以用于替代synchronized
 * 本例中由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 * 这里复习synchronized最原始的定义
 */
public class ReentrantLock2 {

	Lock lock = new ReentrantLock();

	public  void m1() {
		lock.lock();//相当于synchronized(this){}
		try {
			for(int i=0; i<10; i++) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"m1打印  "+i);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//手动释放锁
			lock.unlock();
		}



	}

	public  void m2() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName()+"m2打印");
		}finally {
			lock.unlock();
		}
	}


	public static void main(String[] args) {

		ReentrantLock2 re1 = new ReentrantLock2();

		new Thread(()->re1.m1(),"t1").start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//线程2挤不进去线程1
		new Thread(re1::m2,"t2").start();




	}





}
