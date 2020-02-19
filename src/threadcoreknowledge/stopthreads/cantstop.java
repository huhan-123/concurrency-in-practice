package threadcoreknowledge.stopthreads;

/**
 * @Author: huhan
 * @Date 2020/2/17 3:43 下午
 * @Description 如果while里面放try/catch，会导致中断失效
 * @Verion 1.0
 */
public class cantstop {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            int num = 0;
            while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
                try {
                    //当中断异常被抛出后，中断信号会重置，因此会导致不能推出while循环
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
