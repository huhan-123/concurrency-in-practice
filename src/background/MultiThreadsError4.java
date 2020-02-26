package background;

/**
 * @Author: huhan
 * @Date 2020/2/21 8:42 下午
 * @Description
 * @Verion 1.0
 */
public class MultiThreadsError4 {
    static Point point;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            new Point(1, 1);
        });
        thread.start();
        try {
//            Thread.sleep(10);
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(point);
    }
}
class Point{
    final int x;
    final int y;

    public Point(int x, int y) {
        this.x = x;
        MultiThreadsError4.point=this;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
