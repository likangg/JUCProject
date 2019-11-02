package juc003;
import java.util.concurrent.TimeUnit;

/**
 *   对业务写方法加锁，而对业务读方法不加锁，
 * 容易出现 脏读问题
 * <p>
 * 因为，在执行写的过程中，因为读操作没有加锁，所以读会读取到写未改完的脏数据
 * 解决办法，给读写都加锁
 */
/**
 * @Author: ；likang
 * @Description:
 * @Date:Create：in 2019/11/1 17:37
 * @Version：1.0
 */
public class Account {
    String name;
    double balance;

    //写方法加锁
    public synchronized void set(String name, double balance) {
        this.name = name;

        try {

            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.balance = balance;
    }
    //读方法不加锁 脏读  会读到写的过程中未完成数据
   /*
    public double getBalance(String name) {
    	//通过名字获得余额
        return this.balance;
    }
    */
    //脏读解决办法 读办法也加锁
    public synchronized double getBalance(String name) {
        //通过名字获得余额
        return this.balance;
    }




    public static void main(String[] args) {
        Account a = new Account();
        new Thread(() -> a.set("likang", 100.0)).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println(a.getBalance("likang")); // 0.0
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance("likang")); // 100
    }
}
