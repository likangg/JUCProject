package juc024;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
/*
 * Vector容器买票
 * 依然会出现线程安全问题
 * 为什么？
 * 因为Vector容器虽然是线程安全的，而且remove方法是源自性的，
 * 但是整个程序中 判断和 减票 操作 依然是分离的
 * 
 * */
public class TicketSeller2 {

	private static Vector<String> tickets = new Vector<>();
	
	static {
		for(int i = 0;i<30;i++) tickets.add("电影票编号："+i);
	}
	
	public static void main(String[] args) {
		for(int i = 0;i<3;i++) {
			new Thread(()->{
				while(tickets.size()>0){
					try {
						TimeUnit.MICROSECONDS.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"销售了--"+tickets.remove(0));
				}
				
				
			},"窗口"+i).start();
			
		}
	}
	
	
}
