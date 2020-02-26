package deadlock;

import java.util.Random;

/**
 * @Author: huhan
 * @Date 2020/2/23 8:42 下午
 * @Description 多人转账时也有可能发生死锁
 * @Verion 1.0
 */
public class MultiTransferMoney {
    private static final int NUM_ACCOUNTS = 500;
    private static final int NUM_MONEY = 1000;
    private static final int NUM_ITERATIONS = 1000000;
    private static final int NUM_THREADS = 20;

    static class Account {
        public int balance;

        public Account(int balance) {
            this.balance = balance;
        }
    }

    public static void transferMoney(Account from, Account to, int amount) {
        synchronized (from) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (to) {
                if (from.balance - amount < 0) {
                    System.out.println("余额不足，转账失败");
                } else {
                    from.balance = from.balance - amount;
                    to.balance = to.balance + amount;
                    System.out.println("成功转账" + amount + "元");
                }
            }
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        Account[] accounts = new Account[NUM_ACCOUNTS];
        for (int i = 0; i < NUM_ACCOUNTS; i++) {
            accounts[i] = new Account(NUM_MONEY);
        }
        class transferThread extends Thread{
            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAccount = random.nextInt(NUM_ACCOUNTS);
                    int toAccount = random.nextInt(NUM_ACCOUNTS);
                    int amount = random.nextInt(NUM_MONEY);
                    transferMoney(accounts[fromAccount],accounts[toAccount],amount);
                }
            }
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            new transferThread().start();
        }
    }

}

