package background;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: huhan
 * @Date 2020/2/21 8:24 下午
 * @Description 发布逸出
 * @Verion 1.0
 */
public class MultiThreadsError3 {
    private Map<String,String> states;

    public MultiThreadsError3() {
        states = new HashMap<>();
        states.put("1","周一");
        states.put("2","周二");
        states.put("3","周三");
        states.put("4","周四");
    }

    public Map<String, String> getStates() {
//        return states;
        //可以返回副本避免对象逸出
        return new HashMap<>(states);
    }

    public static void main(String[] args) {
        MultiThreadsError3 multiThreadsError3 = new MultiThreadsError3();
        new Thread(()->{
            Map<String, String> states = multiThreadsError3.getStates();
            states.put("1","周日");
        }).start();
        new Thread(()->{
            Map<String, String> states = multiThreadsError3.getStates();
            System.out.println(states.get("1"));
        }).start();
    }
}
