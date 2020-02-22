package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: huhan
 * @Date 2020/2/19 9:18 下午
 * @Description 用wait/notify来实现
 * @Verion 1.0
 */
public class ProducerConsumerModel {
    public static void main(String[] args) {
        EventStorage eventStorage = new EventStorage();
        Thread producer = new Thread(new Producer(eventStorage));
        Thread consumer = new Thread(new Consumer(eventStorage));
        producer.start();
        consumer.start();
    }

}

class EventStorage {
    public int maxSize;
    public LinkedList<Integer> storage;

    public EventStorage() {
        this.maxSize = 10;
        this.storage = new LinkedList<>();
    }

    public synchronized void put(int num) throws InterruptedException {
        if (storage.size() == maxSize) {
            wait();
        }
        storage.add(num);
        notify();
    }

    public synchronized int poll() throws InterruptedException {
        if (storage.size() == 0) {
            wait();
        }
        notify();
        return storage.poll();
    }
}

class Producer implements Runnable {

    private EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                storage.put(i);
                System.out.println("我是生产者，我生产了数字："+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable{
    private EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                System.out.println("我是消费者，我消费了数字："+storage.poll());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


