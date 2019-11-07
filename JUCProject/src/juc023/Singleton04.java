package juc023;

/**
 * 多线程安全的单例模式  实例四：
 * 使用内部类的单例模式
 * 
 * 更好的是采用这种方式，既不用加锁，也能实现懒加载
 */
public class Singleton04 {
	
	//私有单例对象,在静态内部类中
	private static class Inner{
		private static Singleton04 instance = new Singleton04();
	}
	
	//构造方法私有
	private Singleton04() {
		System.out.println("instance");
	}
	
	//对外提供获取单例方法
	public static Singleton04 getInstance() {
		return Inner.instance;
	}
	
	public static void main(String[] args) {
		
	}
	
	
	

}
