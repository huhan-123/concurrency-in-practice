package threadcoreknowledge.stopthreads;

/**
 * @Author: huhan
 * @Date 2020/2/17 3:13 下午
 * @Description 如果在执行过程中，每次循环都会调用sleep或wait等方法，那么不需要每次迭代都检查是否已中断
 * @Verion 1.0
 */
public class RightWayStopThreadWithSleepEveryLoop {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                int num = 0;
                while (num <= 10000) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                    Thread.sleep(10);
                }
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
