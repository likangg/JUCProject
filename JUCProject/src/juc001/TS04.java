package juc001;

public class TS04 {

	//private int count = 1000;
	private static int count = 1000;

	//synchronized使用在静态方法上，相当于synchronized(本类.class){}
	public synchronized static void m1() {//synchronized使用在静态方法上，相当于synchronized(TS04.class){}

		count--;
		System.out.println(Thread.currentThread().getName()+":count="+count);
		//}
	}

	//静态的属性和方法，不能new，所以没有this存在，对象可以直接访问。
	public  static void m2() {

		//synchronized(this){//静态的属性和方法，不能new，所以没有this存在，对象可以直接访问。
		//所以用本类来锁定
		synchronized(TS04.class) {
			count--;
			System.out.println(Thread.currentThread().getName()+":count="+count);
		}
	}


}
