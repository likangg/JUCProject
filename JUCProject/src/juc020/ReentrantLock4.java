package juc020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 和 synchronized 的区别
 *
 * ReentrantLock 可以调用 lockInterruptibly方法，可以对线程interrupt方法做出响应
 * 在一个线程等待锁的过程中，可以被interrupt方法打断等待。
 */
public class ReentrantLock4 {

	public static void main(String[] args) {

		Lock lock = new ReentrantLock();

		//开启线程1
		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+"启动了 ");
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName()+"拿到了锁 ");
				TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);//睡死了
				System.out.println(Thread.currentThread().getName()+"end ");
			}catch(Exception e) {
				System.out.println(Thread.currentThread().getName()+"interrupted ");
			}finally {
				lock.unlock();
			}
		},"t1").start();



		//开启线程2
		Thread t2 = new Thread(()->{
			System.out.println(Thread.currentThread().getName()+"启动了 ");
			//lock.lock();
			//可以对Interrupted做出响应
			try {
				lock.lockInterruptibly();
				System.out.println(Thread.currentThread().getName()+"拿到了锁 ");
				TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
				System.out.println(Thread.currentThread().getName()+"end ");
			}catch(Exception e) {
				System.out.println(Thread.currentThread().getName()+"interrupted ");
			}finally {
				lock.unlock();
			}
		});
		t2.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		t2.interrupt();//t1睡死了，t2打断等待


	}





}
