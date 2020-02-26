package singleton;

/**
 * @Author: huhan
 * @Date 2020/2/23 7:34 下午
 * @Description 枚举单例（推荐用）
 * 枚举单例是最好的单例模式实现方式
 * 好处：1.懒加载；2.线程安全；3.避免反序列化破坏单例
 * @Verion 1.0
 */
public enum  Singleton8 {
    INSTANCE;

    public void whatever(){}
}
