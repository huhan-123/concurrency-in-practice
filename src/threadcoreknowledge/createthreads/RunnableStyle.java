package threadcoreknowledge.createthreads;

/**
 * @Author: huhan
 * @Date 2020/2/16 2:36 下午
 * @Description 用runnable方式创建线程
 * @Verion 1.0
 */
public class RunnableStyle implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("用Runnable方法是实现线程");
    }
}
