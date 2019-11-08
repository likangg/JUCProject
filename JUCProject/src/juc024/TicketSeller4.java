package juc024;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/*List容器装票  会出现线程安全问题
 *  效率高的解决办法：
 *  使用队列
 * */
public class TicketSeller4 {

	//List容器装票
	private static Queue<String> tickets = new ConcurrentLinkedQueue<>();
	
	static {
		for(int i = 0;i<100000;i++) tickets.add("电影票编号："+i);
	}
	
	public static void main(String[] args) {
		for(int i = 0;i<1000;i++) {
			new Thread(()->{
				while(true) {
					String s = tickets.poll();
					if(s == null) break;
					else System.out.println(Thread.currentThread().getName()+"销售了--"+s);
				}
			
			},"窗口"+i).start();
			
		}
	}
	
	
}
