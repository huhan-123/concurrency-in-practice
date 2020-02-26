package threadcoreknowledge.uncaughtexception;

/**
 * @Author: huhan
 * @Date 2020/2/21 9:56 上午
 * @Description 单线程，抛出，处理，有异常堆栈
 * 多线程，子线程发生异常，不会影响主线程的执行，主线程不能捕获子线程的异常
 * @Verion 1.0
 */
public class ExceptionInChildThread {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            throw new RuntimeException();
        });
        thread.start();
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
