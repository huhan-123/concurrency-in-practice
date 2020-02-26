package jmm;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: huhan
 * @Date 2020/2/23 10:15 上午
 * @Description 演示重排序现象
 * 直到达到某个条件才停止
 * @Verion 1.0
 */
public class OutOfOrderExecution {
    private static int x = 0, y = 0, a = 0, b = 0;

    public static void main(String[] args) {
        int i = 0;
        while (true) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            CountDownLatch countDownLatch = new CountDownLatch(1);
            Thread thread1 = new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });
            Thread thread2 = new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                y = a;
            });
            thread1.start();
            thread2.start();
            countDownLatch.countDown();
            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //由于重排序和多线程相互执行，会有四种结果（0，1），（1，0），（0，0），（1，1）
            System.out.println("第" + i + "次执行："+"x=" + x + ",y=" + y);
            if (x == 0 && y == 0) {
                break;
            }
        }

    }
}
