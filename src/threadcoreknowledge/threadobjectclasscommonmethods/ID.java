package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @Author: huhan
 * @Date 2020/2/21 9:22 上午
 * @Description ID从1开始，JVN运行起来后，我们自己创建的线程
 * 的ID并不是1(因为JVM启动时默认启动很多守护线程供用户线程使用)
 * @Verion 1.0
 */
public class ID {
    public static void main(String[] args) {
        System.out.println("主线程ID："+Thread.currentThread().getId());
        Thread thread = new Thread();
        System.out.println("子线程ID："+thread.getId());
    }
}
