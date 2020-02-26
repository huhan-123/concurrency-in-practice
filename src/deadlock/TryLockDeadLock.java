package deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: huhan
 * @Date 2020/2/24 11:45 上午
 * @Description 用try lock来避免锁
 * @Verion 1.0
 */
public class TryLockDeadLock implements Runnable {
    public final static Lock lock1 = new ReentrantLock();
    public final static Lock lock2 = new ReentrantLock();
    int flag;

    public TryLockDeadLock(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (flag == 1) {
                try {
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                        System.out.println(Thread.currentThread().getName() + "得到了lock1");
                        if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                            System.out.println(Thread.currentThread().getName() + "得到了lock2");
                            lock2.unlock();
                            lock1.unlock();
                            break;
                        } else {
                            System.out.println(Thread.currentThread().getName() + "获取lock2失败，已重试");
                            lock1.unlock();
                            Thread.sleep(1000);
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "获取lock1失败,已重试");
                        Thread.sleep(new Random().nextInt(1000));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (flag == 0) {
                try {
                    if (lock2.tryLock(800,TimeUnit.MILLISECONDS)) {
                        System.out.println(Thread.currentThread().getName() + "得到了lock2");
                        if (lock1.tryLock(800,TimeUnit.MILLISECONDS)) {
                            System.out.println(Thread.currentThread().getName() + "得到了lock1");
                            lock1.unlock();
                            lock2.unlock();
                            break;
                        } else {
                            System.out.println(Thread.currentThread().getName() + "获取lock1失败，已重试");
                            lock2.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "获取lock2失败,已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        TryLockDeadLock tryLockDeadLock1 = new TryLockDeadLock(1);
        TryLockDeadLock tryLockDeadLock2 = new TryLockDeadLock(0);
        new Thread(tryLockDeadLock1, "threadA").start();
        new Thread(tryLockDeadLock2, "threadB").start();
    }
}
