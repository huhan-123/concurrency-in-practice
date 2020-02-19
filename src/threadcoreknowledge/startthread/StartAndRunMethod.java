package threadcoreknowledge.startthread;

/**
 * @Author: huhan
 * @Date 2020/2/17 10:25 上午
 * @Description 对比start和run两种启动线程的方式
 * @Verion 1.0
 */
public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        runnable.run();

        Thread thread = new Thread(runnable);
        thread.run();
        thread.start();
    }
}
