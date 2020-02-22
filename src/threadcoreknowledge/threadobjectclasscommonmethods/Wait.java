package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @Author: huhan
 * @Date 2020/2/19 7:53 下午
 * @Description 展示wait和notify的基本用法
 * 1.研究代码执行顺序
 * 2.证明wait释放锁
 * @Verion 1.0
 */
public class Wait {
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
                System.out.println("我是线程" + Thread.currentThread().getName() + ",我准备唤醒A");
                //notify执行后不会马上归还锁，而是执行完线程后归还
                object.notify();
                System.out.println("我是线程" + Thread.currentThread().getName()+",我唤醒了A");
            }
        }, "threadB");

        threadA.start();
        //让threadA先执行
        Thread.sleep(100);
        threadB.start();
    }

}
