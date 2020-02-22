package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @Author: huhan
 * @Date 2020/2/19 8:43 下午
 * @Description 3个线程，线程1和线程2首先被阻塞，线程3唤醒它们。
 * @Verion 1.0
 */
public class WaitAndNotifyAll {
    public static final Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            synchronized (object) {
                System.out.println("我是线程" + Thread.currentThread().getName() + ",我准备等待");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("我是线程" + Thread.currentThread().getName()+",我已经醒了");
            }
        }, "threadA");

        Thread threadB = new Thread(() -> {
            synchronized (object) {
                System.out.println("我是线程" + Thread.currentThread().getName() + ",我准备等待");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("我是线程" + Thread.currentThread().getName()+",我已经醒了");
            }
        }, "threadB");

        Thread threadC = new Thread(() -> {
            synchronized (object) {
                System.out.println("我是线程" + Thread.currentThread().getName() + ",我准备唤醒A,B");
                //notify执行后不会马上归还锁，而是执行完线程后归还
                object.notifyAll();
//                object.notify();
                System.out.println("我是线程" + Thread.currentThread().getName()+",我唤醒了A,B");
            }
        }, "threadC");

        threadA.start();
        threadB.start();
        Thread.sleep(10);
        threadC.start();
    }
}
