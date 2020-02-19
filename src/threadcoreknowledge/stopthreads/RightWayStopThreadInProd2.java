package threadcoreknowledge.stopthreads;

/**
 * @Author: huhan
 * @Date 2020/2/17 4:35 下午
 * @Description 最佳实践2：在catch子语句中调用Thread.currentThread.interrupt()方法
 * 来恢复设置中断状态，以便于后续的执行中，依然能够检查到刚才发生了中断
 * @Verion 1.0
 */
public class RightWayStopThreadInProd2 implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("go");
            reInterrupt();
        }
    }

    private void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            //中断异常被捕获后中断状态会被重置，这里要恢复中断状态
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
