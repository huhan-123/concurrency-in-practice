package background;

/**
 * @Author: huhan
 * @Date 2020/2/21 8:58 下午
 * @Description 观察者模式（不能在构造方法中注册监听器）
 * @Verion 1.0
 */
public class MultiThreadsError5 {
    int count;

    public MultiThreadsError5(MySource mySource) {
        //这里监听器被过早的发布，导致初始化还未完成就可以被其他线程调用
        mySource.registerListener((eventListener) -> System.out.println("\n我得到的数字是" + count));
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new Event() {
            });
        }).start();
        MultiThreadsError5 multiThreadsError5 = new MultiThreadsError5(mySource);
    }

    public static class MySource {
        private EventListener eventListener;

        void registerListener(EventListener eventListener) {
            this.eventListener = eventListener;
        }

        void eventCome(Event event) {
            if (eventListener != null) {
                eventListener.onEvent(event);
            } else {
                System.out.println("还未初始化完毕");
            }
        }

    }

    interface EventListener {
        void onEvent(Event event);
    }

    interface Event {

    }
}
