package juc023;

/**
 * 多线程安全的单例模式  实例三：
 * 双重锁机制
 */
public class Singleton03 {
	
	
	//私有单例对象
	private static Singleton03 instance;
	
	//构造方法私有
	private Singleton03() {
		
	}
	
	//对外提供获取单例方法
	public static Singleton03 getInstance() {
		if( instance == null) {
			synchronized(Singleton03.class) {
				if( instance == null) {
					instance = new Singleton03();
				}
			}
		}
		return instance;
	}
	

}
