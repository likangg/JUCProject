package juc021;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 经典面试题：写一个固定容量的容器，拥有put和get方法，以及getCount方法
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * <p>
 * 点：生产者消费者模式
 * <p>
 * 如果调用 get方法时，容器为空，get方法就需要阻塞等待
 * 如果调用 put方法时，容器满了，put方法就需要阻塞等待
 * <p>
 * 实现方式：
 * 1. wait/notify
 * 2. Condition
 * <p>
 * <p>
 * 使用Lock和Condition实现，可以精确唤醒某些线程
 */
public class MyContainer2<T> {

	private final LinkedList<T> lists = new LinkedList<>();
	private final int MAX = 10;//最多10个元素
	private int count = 0;

	private Lock lock = new ReentrantLock();
	private Condition producer = lock.newCondition();
	private Condition consumer = lock.newCondition();


	public  void put(T t) {
		try {
			lock.lock();
			while(lists.size() == MAX) {//柜台放满了
				try {
					//柜台放满，商品处于等待状态
					producer.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//如果柜台未满，添加商品
			lists.add(t);
			System.out.println(Thread.currentThread().getName()+ "生产了"+t.toString());
			++count;//更新商品数量
			consumer.signalAll();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}

	}
	public  T get() {
		T t = null;
		try {
			lock.lock();
			while(lists.size() == 0) {//如果柜台没有商品，等着进货
				try {
					consumer.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//移除
			t = lists.removeFirst();
			count--;//更新商品数量
			producer.signalAll();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		return t;
	}

	public int getCount() {
		return lists.size();
	}

	public static void main(String[] args) {
		MyContainer2<String>  mycontainer = new MyContainer2<>();
		//启动消费者线程
		for(int i = 0 ;i <10;i++) {
			new Thread(()->{
				for(int j = 0 ;j < 5 ;j++) {
					System.out.println(Thread.currentThread().getName()+ mycontainer.get());
				}
			},"C"+i).start();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//启动生产者线程
		for(int i = 0 ;i <2;i++) {
			new Thread(()->{
				for(int j = 0 ;j < 25 ;j++) {
					mycontainer.put(Thread.currentThread().getName()+"  "+ j);
				}
			},"p"+i).start();
		}



	}

}
