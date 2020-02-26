package deadlock;

/**
 * @Author: huhan
 * @Date 2020/2/24 10:06 上午
 * @Description 演示哲学家就餐问题导致的死锁
 * @Verion 1.0
 */
public class DiningPhilosophers {
    static int NUM_PHILOSOPHER = 5;

    public static class Philosopher implements Runnable {
        private Object leftChopstick;
        private Object rightChopstick;

        public Philosopher(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doAction(Thread.currentThread().getName() + " is thinking");
                    synchronized (leftChopstick) {
                        doAction(Thread.currentThread().getName() + " pick up left chopstick");
                        synchronized (rightChopstick) {
                            doAction(Thread.currentThread().getName() + " pick up right chopstick");
                            doAction(Thread.currentThread().getName() + " is eating");
                            doAction(Thread.currentThread().getName() + " put down right chopstick");
                        }
                        doAction(Thread.currentThread().getName() + " put down left chopstick");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void doAction(String action) throws InterruptedException {
            System.out.println(action);
            Thread.sleep((long) (Math.random() * 10));
        }

        public static void main(String[] args) {
            Object[] chopsticks = new Object[NUM_PHILOSOPHER];
            for (int i = 0; i < NUM_PHILOSOPHER; i++) {
                chopsticks[i] = new Object();
            }
            for (int i = 0; i < NUM_PHILOSOPHER; i++) {
                Object leftChockstick = chopsticks[i];
                Object rightChockstick = chopsticks[(i + 1) % 5];
                if (i == NUM_PHILOSOPHER - 1) {
                    new Thread(new Philosopher(rightChockstick, leftChockstick), "哲学家" + (i + 1)).start();
                } else {
                    new Thread(new Philosopher(leftChockstick, rightChockstick), "哲学家" + (i + 1)).start();
                }
            }
        }
    }
}
