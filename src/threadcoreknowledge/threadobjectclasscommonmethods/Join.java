package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @Author: huhan
 * @Date 2020/2/20 8:00 下午
 * @Description 演示join，注意语句输出顺序，会变化。
 * @Verion 1.0
 */
public class Join {
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "准备休眠");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "休眠完毕");
        }, "threadA");
        Thread threadB = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "准备休眠");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "休眠完毕");
        }, "threadB");
        threadA.start();
        threadB.start();
        System.out.println("开始等待子线程运行完毕");
        threadA.join();
        threadB.join();
        System.out.println("所有子线程执行完毕");
    }
}
