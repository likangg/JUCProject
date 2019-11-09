package juc025;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/*  实验
 *  1、Hashtable<>();   默认已经加锁，效率低，很古老
 *  2、ConcurrentHashMap 高并发 HashMap，比Hashtable效率高一些
 *  3、ConcurrentSkipMap<>() 高并发并且插入数据排好顺序的情况下
 *  ConcurrentSkipMap 跳表：skipMap: https://blog.csdn.net/sunxianghuang/article/details/52221913
 * 
 *  Hashtable<>();   默认已经加锁，效率低，很古老
 *  HashMap<>();// 需要往上加锁，Collections.synchronizedXXX
 * */
public class T01_ConcurrentMap {

	public static void main(String[] args) {
		Map<String,String> map = new ConcurrentHashMap<>();//272
		//Map<String,String> map = new ConcurrentSkipMap<>();
		//Map<String,String> map = new Hashtable<>();// 421
		//Map<String,String> map = new HashMap<>();//
		//Map<String,String> map = new Hashtable<>();
		
		Random r = new Random();
		Thread[] ths = new Thread[100];
		
		CountDownLatch latch = new CountDownLatch(ths.length);
		long start = System.currentTimeMillis();
		for(int i = 0;i<ths.length;i++) {
			ths[i] = new Thread(()->{
				for(int j=0;j<10000;j++) map.put("a"+r.nextInt(100000), "a"+r.nextInt(100000));
				latch.countDown();
			});
		}
		
		Arrays.asList(ths).forEach(t->t.start());
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		System.out.println(map.size());
	}
	
	
	
}
