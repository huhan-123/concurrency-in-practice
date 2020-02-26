package background;

import javax.xml.transform.Source;

/**
 * @Author: huhan
 * @Date 2020/2/21 9:47 下午
 * @Description 用工厂模式修复刚才的初始化问题
 * @Verion 1.0
 */
public class MultiThreadsError7 {
    int count;
    private EventListener eventListener;

    private MultiThreadsError7() {
        this.eventListener = event -> System.out.println("\n我得到的数字是" + count);
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static MultiThreadsError7 getInstance(MySource mySource){
        MultiThreadsError7 multiThreadsError7 = new MultiThreadsError7();
        mySource.registerListener(multiThreadsError7.eventListener);
        return multiThreadsError7;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new Event() {
            });
        }).start();
        MultiThreadsError7.getInstance(mySource);
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
