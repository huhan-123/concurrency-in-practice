package background;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: huhan
 * @Date 2020/2/21 3:10 下午
 * @Description 第一种:运行结果出错
 * 演示次数不准确（减少），找出具体出错位置
 * @Verion 1.0
 */
public class MulitThreadsError implements Runnable {
    static int index = 0;
    boolean[] marked = new boolean[100000];
    static AtomicInteger realIndex = new AtomicInteger();
    static AtomicInteger wrongCount = new AtomicInteger();
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            synchronized (this) {
                if (marked[index] && marked[index - 1]) {
                    wrongCount.incrementAndGet();
                    System.out.println("发生错误" + index);
                }
                marked[index] = true;
            }
        }
    }

    public static void main(String[] args) {
        MulitThreadsError runnable = new MulitThreadsError();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发生错误次数：" + wrongCount);
        System.out.println("表面上运行的次数：" + index);
        System.out.println("真正运行的次数：" + realIndex);
    }
}
