package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.TimeUnit;

/**
 * @Author: huhan
 * @Date 2020/2/20 8:10 下午
 * @Description 演示join期间被中断的效果
 * @Verion 1.0
 */
public class JoinInterrupt {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            try {
                mainThread.interrupt();
                TimeUnit.SECONDS.sleep(5);
                System.out.println("thread1执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("主线程被打断的异常是在这里被处理的");
            thread.interrupt();
        }
        System.out.println("主线程继续执行");
    }
}
