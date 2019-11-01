package juc002;

public class TS05 implements Runnable {

    private  int count = 10;


    /*不加锁 打印：
        Thread1:count=7
        Thread3:count=6
        Thread2:count=7
        Thread0:count=7
        Thread4:count=5
    */
    public /* synchronized */  void run() {
        count--;
        System.out.println(Thread.currentThread().getName()+":count="+count);

    }
	/*加锁打印：
	 *  Thread0:count=9
		Thread1:count=8
		Thread4:count=7
		Thread3:count=6
		Thread2:count=5
	 * */



    public static void main(String[] args) {

        TS05 ts = new TS05();
        for(int i =0; i<5 ;i++) {
            new Thread(ts,"Thread"+i).start();

        }


    }





}
