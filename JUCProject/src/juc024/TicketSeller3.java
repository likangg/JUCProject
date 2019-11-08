package juc024;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/*List容器装票
 * 
 * 会出现线程安全问题
 * 
 * 
 * 解决办法：：加锁？能不能解决
 * 
 * 可以解决，但是效率不高
 * */
public class TicketSeller3 {

	//List容器装票
	private static List<String> tickets = new ArrayList<>();
	
	static {
		for(int i = 0;i<5000;i++) tickets.add("电影票编号："+i);
	}
	
	public static void main(String[] args) {
		for(int i = 0;i<10;i++) {
			new Thread(()->{
				while(true) {
					synchronized(tickets) {
						if(tickets.size() <= 0) break;
						try {
							TimeUnit.MICROSECONDS.sleep(5);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()+"销售了--"+tickets.remove(0));
					}
				}
			
			},"窗口"+i).start();
			
		}
	}
	
	
}
