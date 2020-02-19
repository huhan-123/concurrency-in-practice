package threadcoreknowledge.stopthreads.volatiledemo;

/**
 * @Author: huhan
 * @Date 2020/2/18 4:07 下午
 * @Description 演示用volatile的局限：part1 看似可行
 * @Verion 1.0
 */
public class WrongWayVolatile implements Runnable {
    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num < Integer.MAX_VALUE && !canceled) {

                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WrongWayVolatile runnable = new WrongWayVolatile();
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runnable.canceled = true;
    }
}
