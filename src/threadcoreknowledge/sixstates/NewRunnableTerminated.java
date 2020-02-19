package threadcoreknowledge.sixstates;

/**
 * @Author: huhan
 * @Date 2020/2/19 10:08 上午
 * @Description 展示New,Runnable,Terminated三种状态（其中Runnable状态包括Ready和Running两种状态）
 * @Verion 1.0
 */
public class NewRunnableTerminated implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
        //执行中也是RUNNABLE状态
        System.out.println(Thread.currentThread( ).getState());
    }

    public static void main(String[] args) throws InterruptedException {
        NewRunnableTerminated newRunnableTerminated = new NewRunnableTerminated();
        Thread thread = new Thread(newRunnableTerminated);
        System.out.println(thread.getState());
        thread.start();
        //还未开始执行是RUNNABLE
        System.out.println(thread.getState());
        Thread.sleep(10);
        Thread.sleep(100);
        System.out.println(thread.getState());
    }
}
