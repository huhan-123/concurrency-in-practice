package singleton;

/**
 * @Author: huhan
 * @Date 2020/2/23 7:12 下午
 * @Description 双重检查（推荐面试时使用）
 * 优点：线程安全；延迟加载；效率较高
 * @Verion 1.0
 */
public class Singleton6 {
    private volatile static Singleton6 instance;

    private Singleton6() {
    }

    public static Singleton6 getInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                if (instance == null) {
                    //新建对象不是原子操作，可能发生重排序，所以单例对象需要用volatile修饰
                    //1.创建空对象；2.调用构造方法；3.赋值
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}
