package juc001;

public class TS01 {


	private int count = 1000;
	private Object o = new Object();

	public void m() {

		//synchronized代码块锁定Object对象， 然后执行代码块
		synchronized(o){
			count--;
			System.out.println(Thread.currentThread().getName()+":count="+count);
		}
		
		//synchronized{}代码块执行完毕之后，释放对象锁。

	}


}
