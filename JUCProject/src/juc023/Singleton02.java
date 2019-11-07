package juc023;

/**
 * 多线程安全的单例模式  实例二：
 * 使用同步方法
 */
public class Singleton02 {
	
	
	//私有单例对象
	private static Singleton02 instance;
	
	//构造方法私有
	private Singleton02() {
		
	}
	
	//对外提供获取单例方法
	public static synchronized Singleton02 getInstance() {
		if( instance == null) {
			instance = new Singleton02();
		}
		return instance;
	}
	

	
}
