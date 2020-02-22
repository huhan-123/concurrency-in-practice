package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @Author: huhan
 * @Date 2020/2/19 10:11 下午
 * @Description 用程序实现两个线程交替打印0～100的奇偶数
 * @Verion 1.0
 */
public class WaitNotifyPrintOddEvenSyn {
    public static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread evenThread = new Thread(() -> {
            synchronized (lock) {
                while (num < 100) {
                    if ((num & 1) == 0) {
                        System.out.println(Thread.currentThread().getName()+":"+num++);
                        try {
                            lock.notify();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                lock.notify();
            }
        },"偶数线程");
        Thread oddThread = new Thread(() -> {
            synchronized (lock) {
                while (num<100){
                    if ((num & 1) == 1) {
                        System.out.println(Thread.currentThread().getName()+":"+num++);
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                lock.notify();
            }
        },"奇数线程");

        evenThread.start();
        Thread.sleep(10);
        oddThread.start();
    }
}
