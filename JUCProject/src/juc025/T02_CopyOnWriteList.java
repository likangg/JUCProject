package juc025;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.*;
/**
 * 写时复制容器：读数据效率极高，写数据效率极低
 * 写时复制List：
 * 当发生写操作(添加、删除、修改)时，容器就会复制原有容器一份然后对新操作进行写操作，然后再将引用转向新的容器
 * 好处：保证读操作不需要锁也能正常访问，是一种读写分离的实现方式
 * 缺点：写的效率极低，特定场景下才会使用到
 * -------------------------------------
 * ArrayList<>()： 会出现并发问题，需要加锁，Collections.synchronizedList();方法加锁
 *  
 * Vector<>()：效率稍差，
 * 
 * CopyOnWriteArrayList<>()：效率最差，读的时候不需要加锁 ； 写速极慢，读速极快
 * 	业务情形：用于写的很少，读的很多
 */
public class T02_CopyOnWriteList {


    public static void main(String[] args) {

          List<String> listNoSych = new ArrayList<>();// 92478 不到100000
          List<String> list = Collections.synchronizedList(listNoSych);
     //   List<String> list =  new Vector<>(); // 100000
     //   List<String> list = new CopyOnWriteArrayList<>();  // 100000 写速极慢，读取快

        Random r = new Random();
        Thread[] ths = new Thread[100];

        for (int i = 0; i < ths.length; i++) {
            Runnable task = () -> {
                for (int j = 0; j < 1000; j++) {
                    list.add("a" + r.nextInt(100));
                }
            };
            ths[i] = new Thread(task);

        }
        runAndComputeTime(ths);
        System.out.println(list.size());
        
    }

    static void runAndComputeTime(Thread[] ths) {
        long start = System.currentTimeMillis();
        Arrays.asList(ths).forEach(Thread::start);
        Arrays.asList(ths).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
    
}
