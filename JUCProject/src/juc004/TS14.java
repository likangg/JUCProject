package juc004;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 不要以字符串常量作为锁定对象
 * 在下面的例子中， m1和m2其实是锁定的同一对象
 * 这种情况下，还会可能与其他类库发生死锁，比如某类库中也锁定了字符串 "Hello"
 * 但是无法确认源码的具体位置，所以两个 "Hello" 将会造成死锁
 * 因为你的程序和你用的类库无意间使用了同意把锁
 */

public class TS14{

    String s1 = "Hello";
    String s2 = "Hello";

    public  void m1() {
        synchronized (s1) {

        }
    }

    public  void m2() {
        synchronized (s2) {

        }
    }

}


