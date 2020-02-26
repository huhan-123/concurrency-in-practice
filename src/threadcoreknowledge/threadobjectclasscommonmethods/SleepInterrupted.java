package threadcoreknowledge.threadobjectclasscommonmethods;

import java.sql.Time;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: huhan
 * @Date 2020/2/20 6:25 下午
 * @Description 每隔一秒钟输出当前时间，被中断，观察
 * @Verion 1.0
 */
public class SleepInterrupted {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 6; i++) {
                    System.out.println(new Date());
//                    TimeUnit.HOURS.sleep(3);
//                    TimeUnit.MINUTES.sleep(30);
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            Thread.sleep(6500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
