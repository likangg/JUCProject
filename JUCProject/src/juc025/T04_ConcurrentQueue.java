package juc025;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 同步队列，ConcurrentQueue
 */
public class T04_ConcurrentQueue {


    public static void main(String[] args) {
        
    	 System.out.println("---以下演示单项队列ConcurrentLinkedQueue ");
    	//单项队列ConcurrentLinkedQueue
    	Queue<String> queue = new ConcurrentLinkedQueue<>(); // LinkedQueue，无界队列
        for (int i = 0; i < 10; i++) {
            queue.offer("a" + i); // 有返回值，返回false代表没有加入成功，true 代表成功，并且此方法不会阻塞
        }
       
        System.out.println(queue);
        System.out.println(queue.size());
        System.out.println(queue.poll()); // 取出队头，并删除
        
        System.out.println(queue);
        System.out.println(queue.size());
        
        System.out.println(queue.peek()); // 取出队头，但是不删除队头
        System.out.println(queue.size());
        
        
        System.out.println("---以下演示双端队列 Deque ");
        // 双端队列 Deque 发音： dai ke
        Deque<String> deque = new ConcurrentLinkedDeque<>();
        
        for (int i = 0; i < 5; i++) {
        	deque.addFirst("A" + i); // 有返回值，返回false代表没有加入成功，true 代表成功，并且此方法不会阻塞
        } 
        System.out.println(deque);
        
        for (int i = 0; i < 5; i++) {
        	deque.addLast("B" + i); // 有返回值，返回false代表没有加入成功，true 代表成功，并且此方法不会阻塞
        } 
        System.out.println(deque);
        System.out.println(deque.size());
       
        deque.pollFirst();// 取出队头，但会删除队头
        System.out.println(deque);
        System.out.println(deque.size());
        
        deque.pollLast();
        System.out.println(deque);
        System.out.println(deque.size());
        
        deque.peekFirst();// 取出队头，但是不删除队头
        System.out.println(deque);
        System.out.println(deque.size());
       
        deque.peekLast();
        System.out.println(deque);
        System.out.println(deque.size());
        
    }
    
}
