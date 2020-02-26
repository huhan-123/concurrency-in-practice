package singleton;

/**
 * @Author: huhan
 * @Date 2020/2/23 7:06 下午
 * @Description 懒汉式（线程不安全）（不可用）
 * @Verion 1.0
 */
public class Singleton3 {
    private static Singleton3 instace;

    private Singleton3(){};

    public static Singleton3 getInstance(){
        if (instace ==null){
            instace = new Singleton3();
        }
        return instace;
    }
}
