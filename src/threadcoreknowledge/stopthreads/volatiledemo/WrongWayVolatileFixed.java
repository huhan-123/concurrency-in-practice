package threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: huhan
 * @Date 2020/2/18 4:07 下午
 * @Description 用中断修复刚才无尽等待问题
 * @Verion 1.0
 */
public class WrongWayVolatileFixed {
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
                while (num < Integer.MAX_VALUE && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
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

    public static void main(String[] args) {
        ArrayBlockingQueue storage = new ArrayBlockingQueue<Integer>(10);
        WrongWayVolatileFixed base = new WrongWayVolatileFixed();
        Producer producer = base.new Producer(storage);
        Consumer consumer = base.new Consumer(storage);

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
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
