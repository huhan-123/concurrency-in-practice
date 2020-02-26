package singleton;

/**
 * @Author: huhan
 * @Date 2020/2/23 7:12 下午
 * @Description 懒汉式（线程安全，同步方法）（不推荐用）
 * @Verion 1.0
 */
public class Singleton4 {
    private static Singleton4 instance;

    private Singleton4() {
    }

    public synchronized static Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}
