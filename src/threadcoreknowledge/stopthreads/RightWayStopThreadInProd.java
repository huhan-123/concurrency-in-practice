package threadcoreknowledge.stopthreads;

/**
 * @Author: huhan
 * @Date 2020/2/17 3:59 下午
 * @Description 最佳实践：catch了InterruptedException之后的优先选择是在方法签名中抛出异常，传递中断
 * @Verion 1.0
 */
public class RightWayStopThreadInProd implements Runnable {
    @Override
    public void run() {
        try {
            while (true){
                System.out.println("go");
                throwInMethod();
            }
        } catch (InterruptedException e) {
            System.out.println("保存日志");
            e.printStackTrace();
        }
    }

    private void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println("jgljlsfjls");
    }
}
