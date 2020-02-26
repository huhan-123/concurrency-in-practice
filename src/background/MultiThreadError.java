package background;

/**
 * @Author: huhan
 * @Date 2020/2/21 7:28 下午
 * @Description 第二章线程安全问题，演示死锁
 * @Verion 1.0
 */
public class MultiThreadError {
    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (object1) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (object2) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1) {

                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
