package background;

/**
 * @Author: huhan
 * @Date 2020/2/21 8:58 下午
 * @Description 观察者模式
 * @Verion 1.0
 */
public class MultiThreadsError {
    public static class MySource {
        private EventListener eventListener;

        void registerListener(EventListener eventListener) {
            this.eventListener = eventListener;
        }

        void eventCome(Event event){
            
        }

    }

    interface EventListener {
        void onEvent(Event event);
    }

    interface Event {

    }
}
