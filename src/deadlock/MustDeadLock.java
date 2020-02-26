package deadlock;

/**
 * @Author: huhan
 * @Date 2020/2/23 8:17 下午
 * @Description 必定发生死锁的情况
 * @Verion 1.0
 */
public class MustDeadLock implements Runnable {
    int flag;
    Object object1 = new Object();
    Object object2 = new Object();

    public MustDeadLock(int flag, Object object1, Object object2) {
        this.flag = flag;
        this.object1 = object1;
        this.object2 = object2;
    }

    @Override
    public void run() {
        if (flag == 1) {
            synchronized (object1) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "得到了object1锁");
                synchronized (object2) {
                    System.out.println("线程" + Thread.currentThread().getName() + "得到了object2锁");
                }
            }
        } else if (flag == 0) {
            synchronized (object2) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "得到了object2锁");
                synchronized (object1) {
                    System.out.println("线程" + Thread.currentThread().getName() + "得到了object1锁");
                }
            }
        }
    }

    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();
        MustDeadLock mustDeadLock1 = new MustDeadLock(1,object1,object2);
        MustDeadLock mustDeadLock2 = new MustDeadLock(0,object1,object2);
        Thread thread1 = new Thread(mustDeadLock1);
        Thread thread2 = new Thread(mustDeadLock2);
        thread1.start();
        thread2.start();
    }
}
