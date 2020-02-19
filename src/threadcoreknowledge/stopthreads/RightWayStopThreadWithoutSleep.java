package threadcoreknowledge.stopthreads;

/**
 * @Author: huhan
 * @Date 2020/2/17 2:02 下午
 * @Description run方法内没有sleep或wait方法时，停止线程
 * @Verion 1.0
 */
public class RightWayStopThreadWithoutSleep implements Runnable {
    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num < Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                System.out.println(num + "是10000的背倍数");
            }
            num++;
        }
        System.out.println("任务运行结束了");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
