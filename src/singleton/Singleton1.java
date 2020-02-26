package singleton;

/**
 * @Author: huhan
 * @Date 2020/2/23 6:59 下午
 * @Description 饿汉式（静态常量）（可用）
 * @Verion 1.0
 */
public class Singleton1 {
    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}
