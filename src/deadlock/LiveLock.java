package deadlock;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: huhan
 * @Date 2020/2/24 3:00 下午
 * @Description 演示活锁问题
 * @Verion 1.0
 */
public class LiveLock {
    static class Spoon {
        private Dinner ownner;

        public Dinner getOwnner() {
            return ownner;
        }

        public Spoon(Dinner ownner) {
            this.ownner = ownner;
        }

        public void setOwnner(Dinner ownner) {
            this.ownner = ownner;
        }

        public synchronized void use() {
            System.out.println(ownner.name + "is eating");
        }
    }

    static class Dinner {
        private String name;
        private boolean isHungry;

        public Dinner(String name, boolean isHungry) {
            this.name = name;
            this.isHungry = isHungry;
        }

        public void eatWith(Spoon spoon, Dinner spouse) {
            while (isHungry) {
                if (spoon.ownner != this) {
                    //如果自己没有拿到勺子，就等一会再吃
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                //如果拿到筷子，且配偶也是饥饿状态，则让配偶先吃
                //这里如果加一个随机限制条件就可以避免活锁
                Random random = new Random();
                if (spouse.isHungry && random.nextInt(10) < 9) {
                    System.out.println(name + ":亲爱的" + spouse.name + ",你先吃吧");
                    spoon.setOwnner(spouse);
                    continue;
                }

                spoon.use();
                isHungry = false;
                spoon.setOwnner(spouse);
                System.out.println(name + ":我吃完了");
            }
        }

    }

    public static void main(String[] args) {
        Dinner husband = new Dinner("牛郎", true);
        Dinner wife = new Dinner("织女", true);
        Spoon spoon = new Spoon(husband);
        new Thread(() -> husband.eatWith(spoon, wife)).start();
        new Thread(() -> wife.eatWith(spoon, husband)).start();
    }
}
