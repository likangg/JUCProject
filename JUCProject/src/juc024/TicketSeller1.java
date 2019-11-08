package juc024;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/*List容器装票
 * 
 * 会出现线程安全问题
 * 
 * */
public class TicketSeller1 {

	//List容器装票
	private static List<String> tickets = new ArrayList<>();
	
	static {
		for(int i = 0;i<30;i++) tickets.add("电影票编号："+i);
	}
	
	public static void main(String[] args) {
		for(int i = 0;i<3;i++) {
			new Thread(()->{
				while(tickets.size()>0){
					System.out.println(Thread.currentThread().getName()+"销售了--"+tickets.remove(0));
				}
				
				
			},"窗口"+i).start();
			
		}
	}
	
	
}
