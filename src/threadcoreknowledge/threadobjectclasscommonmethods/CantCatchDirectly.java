package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @Author: huhan
 * @Date 2020/2/21 10:14 上午
 * @Description 1.不加try catch抛出4个异常，都带线程名字
 * 2.加了try catch，期望捕获到第一个线程的异常，线程2，3，4不应该运行，
 * 希望看到打印出Caught Exception
 * 3.执行时发现，根本没有Caught Exception，线程2，3，4依然运行并且抛出异常
 * 说明线程的异常不能用传统方式捕获（主线程不能捕获子线程的异常）
 * @Verion 1.0
 */
public class CantCatchDirectly implements Runnable {
    public static void main(String[] args) {
        CantCatchDirectly runnable = new CantCatchDirectly();
        Thread thread1 = new Thread(runnable,"thread1");
        Thread thread2 = new Thread(runnable,"thread2");
        Thread thread3 = new Thread(runnable,"thread3");
        Thread thread4 = new Thread(runnable,"thread4");
        try {
            thread1.start();
            Thread.sleep(10);
            thread2.start();
            thread3.start();
            thread4.start();
        } catch (Exception e) {
            System.out.println("我捕获到了异常");
        }
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
