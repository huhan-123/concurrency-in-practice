package singleton;

/**
 * @Author: huhan
 * @Date 2020/2/23 7:26 下午
 * @Description 静态内部类（推荐用）
 * @Verion 1.0
 */
public class Singleton7 {
    private Singleton7() {
    }

    private static class SingletonInstance {
        //外部类加载时不会初始化内部类里面的实例（懒汉），并且jvm保证线程安全
        private final static Singleton7 INSTANCE = new Singleton7();
    }

    public static Singleton7 getInstance() {
        return SingletonInstance.INSTANCE;
    }

}
