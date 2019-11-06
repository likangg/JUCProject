package juc021;

import java.util.LinkedList;

/**
 * 经典面试题：写一个固定容量的容器，拥有put和get方法，以及getCount方法
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 点：生产者消费者模式
 *
 * 如果调用 get方法时，容器为空，get方法就需要阻塞等待
 * 如果调用 put方法时，容器满了，put方法就需要阻塞等待
 *
 * 实现方式：
 * 1. wait/notify
 * 2. Condition
 */
public class MyContainer1<T> {

	private final LinkedList<T> lists = new LinkedList<>();
	private final int MAX = 10;//最多10个元素
	private int count = 0;


	public synchronized void put(T t) {
		while(lists.size() == MAX) {//柜台放满了
			try {
				//柜台放满，商品处于等待状态
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//如果柜台未满，添加商品

		lists.add(t);
		System.out.println(Thread.currentThread().getName()+ "生产了"+t.toString());
		++count;//更新商品数量
		this.notifyAll();//通知消费者进行消费

	}
	public synchronized T get() {
		T t = null;
		while(lists.size() == 0) {//如果柜台没有商品，等着进货
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//移除
		t = lists.removeFirst();
		count--;//更新商品数量
		this.notifyAll();//通知柜台进行生产
		return t;
	}

	public int getCount() {
		return lists.size();
	}

	public static void main(String[] args) {
		MyContainer1<String>  mycontainer = new MyContainer1<>();
		//启动消费者线程
		for(int i = 0 ;i <10;i++) {
			new Thread(()->{
				for(int j = 0 ;j < 5 ;j++) {
					System.out.println(Thread.currentThread().getName()+"消费了" +mycontainer.get());
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
