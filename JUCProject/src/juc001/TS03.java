package juc001;

public class TS03 {

	private int count = 1000;
	//private Object o = new Object();

	//synchronized同步方法，锁定的是方法体内的代码块
	public synchronized void m() {//等同于synchronized(this){}

		//synchronized(this){//每次都要new一个Object对象，太麻烦， 直接用this,本类对象
		count--;
		System.out.println(Thread.currentThread().getName()+":count="+count);
		//}


	}


}
