package threadcoreknowledge.stopthreads;

/**
 * @Author: huhan
 * @Date 2020/2/17 2:26 下午
 * @Description 带有sleep的中断线程的写法
 * @Verion 1.0
 */
public class RightWayStopThreadWithSleep {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                int num = 0;
                while (num <Integer.MAX_VALUE && !Thread.currentThread().isInterrupted()) {
                    if (num % 10000 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                }
                //当线程被阻塞时或阻塞前接收到interrupt信号，阻塞的时候会抛出异常，线程停止
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("sleep被打断,抛出异常");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
