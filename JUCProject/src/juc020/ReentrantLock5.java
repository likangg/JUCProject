package juc020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 和 synchronized 的区别
 * <p>
 * ReentrantLock 可以指定为公平锁，synchronized 是不公平锁
 * 公平锁，先获取锁的人，在锁被释放时，优先获得锁
 * 不公平锁，无论先后，线程调度器将会随机给某个线程锁，不用计算线程时序，效率较高
 */
public class ReentrantLock5 extends Thread{

	private static ReentrantLock lock = new ReentrantLock(true);//参数为true表示公平锁
	//private static ReentrantLock lock = new ReentrantLock();

	public void run() {
		for(int i =0 ;i<100;i++) {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName()+"获得锁   " +i);
			}finally {
				lock.unlock();
			}
		}
	}



	public static void main(String[] args) {

		ReentrantLock5 re5 = new ReentrantLock5();
		Thread th1 = new Thread(re5);
		Thread th2 = new Thread(re5);
		th1.start();
		th2.start();


	}




}
