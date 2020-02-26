package threadcoreknowledge.uncaughtexception;

/**
 * @Author: huhan
 * @Date 2020/2/21 11:48 上午
 * @Description 使用刚才写的UnCaughtExceptionHandler
 * @Verion 1.0
 */
public class UseOwnUnCaughtExceptionHandler implements Runnable{
    public static void main(String[] args) {
        MyUncaughtExceptionHandler myUncaughtExceptionHandler = new MyUncaughtExceptionHandler("myUncaughtExceptionHandler");
        Thread.setDefaultUncaughtExceptionHandler(myUncaughtExceptionHandler);

        UseOwnUnCaughtExceptionHandler runnable = new UseOwnUnCaughtExceptionHandler();
        Thread thread1 = new Thread(runnable,"thread1");
        Thread thread2 = new Thread(runnable,"thread2");
        Thread thread3 = new Thread(runnable,"thread3");
        Thread thread4 = new Thread(runnable,"thread4");
        try {
            thread1.start();
            Thread.sleep(10);
            thread2.start();
            Thread.sleep(10);
            thread3.start();
            Thread.sleep(10);
            thread4.start();
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
