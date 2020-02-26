package threadcoreknowledge.uncaughtexception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: huhan
 * @Date 2020/2/21 11:23 上午
 * @Description 自己的UncaughtExceptionHandler
 * @Verion 1.0
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private String name;

    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING,name+"捕获到线程"+t.getName()+"的异常："+e);
    }
}
