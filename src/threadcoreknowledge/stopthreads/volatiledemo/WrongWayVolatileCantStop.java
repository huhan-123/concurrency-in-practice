package threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: huhan
 * @Date 2020/2/18 4:07 下午
 * @Description 演示用volatile的局限：part1 看似可行
 * 陷入阻塞时，volatile是无法中断线程的
 * 此例中，生产者的生产速度很快，消费者消费速度慢，所以阻塞队列满了之后，生产者会阻塞，等待消费者进一步消费
 * @Verion 1.0
 */
public class WrongWayVolatileCantStop{
    private volatile boolean canceled = false;

    public static void main(String[] args) {
        ArrayBlockingQueue storage = new ArrayBlockingQueue<Integer>(10);
        Producer producer = new Producer(storage);
        Consumer consumer = new Consumer(storage);

        Thread thread = new Thread(producer);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            while (consumer.needMoreNums()) {
                System.out.println(consumer.storage.take()+"被消费了");
                Thread.sleep(100);
            }
            System.out.println("消费者不需要更多数据了");
            producer.canceled = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class Producer implements Runnable {
    public volatile boolean canceled = false;
    BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num < Integer.MAX_VALUE && !canceled) {
                if (num % 100 == 0) {
                    //当生产者因为队列满了阻塞在这里时，如果canceled的值变为true，线程无法停止
                    storage.put(num);
                    System.out.println(num + "是100的倍数,被放到仓库中了");
                }
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者结束运行");
        }
    }
}

class Consumer {
    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums() {
        if (Math.random() > 0.95) {
            return false;
        }
        return true;
    }
}