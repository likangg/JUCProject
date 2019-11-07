package juc023;

/**
 * 多线程安全的单例模式  实例一：
 * 不使用同步锁
 */
public class Singleton01 {
	
	
	//私有单例对象
	private static Singleton01 instance = new Singleton01();
	
	//构造方法私有
	private Singleton01() {
		
	}
	
	//对外提供获取单例方法
	public static Singleton01 getInstance() {
		return instance;
	}
	

}
