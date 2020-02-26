package singleton;

/**
 * @Author: huhan
 * @Date 2020/2/23 7:12 下午
 * @Description 懒汉式（线程不安全）（不可用）
 * @Verion 1.0
 */
public class Singleton5 {
    private static Singleton5 instance;

    private Singleton5() {
    }

    public static Singleton5 getInstance() {
        if (instance == null) {
            synchronized (Singleton5.class) {
                instance = new Singleton5();
            }
        }
        return instance;
    }
}
