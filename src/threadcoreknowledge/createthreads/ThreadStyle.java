package threadcoreknowledge.createthreads;

/**
 * @Author: huhan
 * @Date 2020/2/16 6:17 下午
 * @Description 用Thread方式实现线程
 * @Verion 1.0
 */
public class ThreadStyle extends Thread {
    public static void main(String[] args) {
        new ThreadStyle().start();
    }

    @Override
    public void run() {
        System.out.println("用Thread类实现线程");
    }
}
