package background;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: huhan
 * @Date 2020/2/21 9:33 下午
 * @Description 构造函数中新开线程
 * @Verion 1.0
 */
public class MultiThreadsError6 {
    private Map<String, String> states;

    public MultiThreadsError6() {
        new Thread(() -> {
            states = new HashMap<>();
            states.put("1", "周一");
            states.put("2", "周二");
            states.put("3", "周三");
            states.put("4", "周四");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public Map<String, String> getStates() {
        return states;
    }

    public static void main(String[] args) {
        MultiThreadsError6 multiThreadsError6 = new MultiThreadsError6();
        System.out.println(multiThreadsError6.getStates().get("1"));
    }
}
