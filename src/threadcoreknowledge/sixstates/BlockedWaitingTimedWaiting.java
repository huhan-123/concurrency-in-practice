package threadcoreknowledge.sixstates;

/**
 * @Author: huhan
 * @Date 2020/2/19 4:39 下午
 * @Description 展示Blocked，Waiting，TimedWaiting三种状态
 * @Verion 1.0
 */
public class BlockedWaitingTimedWaiting implements Runnable {

    @Override
    public synchronized void run() {
        try {
            System.out.println(Thread.currentThread().getName()+"准备休眠");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"休眠完毕");
            System.out.println(Thread.currentThread().getName()+"准备等待");
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimedWaiting blockedWaitingTimedWaiting = new BlockedWaitingTimedWaiting();
        Thread threadA = new Thread(blockedWaitingTimedWaiting, "Thread-A");
        Thread threadB = new Thread(blockedWaitingTimedWaiting, "Thread-B");
        threadA.start();
        threadB.start();
        Thread.sleep(100);
        //打印出threadA处于TimeWaiting状态（正在执行thread.sleep()方法）
        System.out.println("threadA:"+threadA.getState());
        //打印出threadB处于Blocked状态（threadB想拿到锁却被threadA占有）
        System.out.println("threadB:"+threadB.getState());
        Thread.sleep(1100);
        //打印出threadA处于Waiting状态（A执行了wait()方法）
        System.out.println("threadA:"+threadA.getState());
    }
}
