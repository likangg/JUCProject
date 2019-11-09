package juc026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPool 
 * 线程池
 */
public class T05_ThreadPool {

    public static void main(String[] args) throws InterruptedException {

    	//new 线程池获得  5 个线程
    	ExecutorService service = Executors.newFixedThreadPool(5);
    	//ExecutorService 中 扔 任务的方法，两个方法
    	//(1)execute()执行Rannble任务类，无返回值  , (2)submit()执行Calable类或者Rannble任务类，可以有返回值
    	for(int i = 0;i<7;i++) {
    		//放入的是任务类
    		service.execute(()->{
    			try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			System.out.println(Thread.currentThread().getName());
    		});
    	}
    	
    	//
    	System.out.println(service);
    	System.out.println(service.isShutdown());
    	service.shutdown();//关闭线程池，等待所有任务执行完会关闭
    	//service.shutdownNow()//线程池立即关闭，不等任务完成就关闭
    	System.out.println(service.isTerminated());//所有任务都执行完了嘛？
    	System.out.println(service.isShutdown());//线程池要关闭？
    	System.out.println(service);
    	
    	
    	TimeUnit.MILLISECONDS.sleep(500);
    	System.out.println(service.isTerminated());
    	System.out.println(service.isShutdown());
    	System.out.println(service);
    	
    	
    	
    	
    }
    
    
}
