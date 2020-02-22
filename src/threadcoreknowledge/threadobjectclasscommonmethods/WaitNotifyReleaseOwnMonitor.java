package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @Author: huhan
 * @Date 2020/2/19 8:56 下午
 * @Description 证明wait只释放当前的那把锁
 * @Verion 1.0
 */
public class WaitNotifyReleaseOwnMonitor {
    public static final Object object1=new Object();
    public static final Object object2=new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            synchronized (object1) {
                System.out.println("我是线程" + Thread.currentThread().getName() + "我获得了object1锁");
                synchronized (object2) {
                    System.out.println("我是线程" + Thread.currentThread().getName() + "我获得了object2锁");
                    try {
                        System.out.println("我是线程"+Thread.currentThread().getName()+"我释放了object2锁");
                        object2.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "threadA");

        Thread threadB = new Thread(() -> {
            synchronized (object2) {
                System.out.println("我是线程" + Thread.currentThread().getName() + "我获得了object2锁");
                synchronized (object1){
                    //因为线程threadA只释放了object2,没有释放object1，所以threadB是获取不到object1的
                    System.out.println("我是线程"+Thread.currentThread().getName()+"我获得了object1锁");
                }
            }
        }, "threadB");

        threadA.start();
        Thread.sleep(10);
        threadB.start();
    }
}
