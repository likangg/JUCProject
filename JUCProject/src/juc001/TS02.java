package juc001;

public class TS02 {

	private int count = 1000;
	//private Object o = new Object();

	public void m() {

		//synchronized代码块锁定Object对象，
		synchronized(this){//每次都要new一个Object对象，太麻烦， 直接用this,本类对象
			count--;
			System.out.println(Thread.currentThread().getName()+":count="+count);
		}
		//synchronized{}代码块执行完毕之后，释放对象锁。

	}


}
