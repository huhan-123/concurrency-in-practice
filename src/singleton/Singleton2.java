package singleton;

/**
 * @Author: huhan
 * @Date 2020/2/23 7:03 下午
 * @Description 饿汉模式（静态代码块） （可用）
 * @Verion 1.0
 */
public class Singleton2 {
    private final static Singleton2 INSTANCE;

    static {
        INSTANCE = new Singleton2();
    }

    private Singleton2(){}

    public static Singleton2 getInstance(){
        return INSTANCE;
    }

}
