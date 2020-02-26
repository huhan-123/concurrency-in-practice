package deadlock;

import java.util.Random;

/**
 * @Author: huhan
 * @Date 2020/2/23 8:42 下午
 * @Description 转账时候遇到死锁，一旦打开注释，便会发生死锁
 * @Verion 1.0
 */
public class TransferMoney implements Runnable {
    Random random = new Random();
    Account a;
    Account b;

    @Override
    public void run() {
        transferMoney(a, b, random.nextInt(200));
    }

    public void transferMoney(Account from, Account to, int amount) {
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
                    System.out.println(from.name+"成功转账" + amount + "元给"+to.name);
                }
            }
        }
    }

    public TransferMoney(Account a, Account b) {
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) {
        Account a = new Account(500,"张三");
        Account b = new Account(500,"李四");
        Thread thread1 = new Thread(new TransferMoney(a, b));
        Thread thread2 = new Thread(new TransferMoney(b, a));
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.name+"的余额："+a.balance);
        System.out.println(b.name+"的余额："+b.balance);
    }

}

class Account {
    int balance;
    String name;

    public Account(int balance, String name) {
        this.balance = balance;
        this.name = name;
    }
}
