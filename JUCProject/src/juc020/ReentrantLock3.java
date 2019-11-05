package juc020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 和 synchronized 的区别:
 * ReentrantLock 可以进行尝试锁定 tryLock 这样无法锁定、或者在指定时间内无法锁定，线程可以决定是否继续等待
 */
public class ReentrantLock3 {

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
		/*
		boolean isGetLock = lock.tryLock();//尝试获取锁，如果获得返回true
		System.out.println(Thread.currentThread().getName()+"m2是否拿到了锁   " +isGetLock);
		if(isGetLock)lock.unlock();
		*/
		//写法2：
		boolean isGetLock = false;
		try {
			//尝试等待5秒锁
			isGetLock = lock.tryLock(5,TimeUnit.SECONDS);
			System.out.println(Thread.currentThread().getName()+"m2是否拿到了锁   " +isGetLock);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//如果获得了锁，手动释放锁
			if(isGetLock)lock.unlock();
		}


	}



	public static void main(String[] args) {

		ReentrantLock3 re1 = new ReentrantLock3();

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
