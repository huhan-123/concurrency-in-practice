package deadlock;

import java.util.Random;

/**
 * @Author: huhan
 * @Date 2020/2/24 9:06 上午
 * @Description 修复转账死锁（通过规定获取锁的顺序一致）
 * @Verion 1.0
 */
public class FixedTransferMoney implements Runnable {
    private Account from;
    private Account to;
    private int amount;

    public FixedTransferMoney(Account from, Account to, int amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    private final static Object lock = new Object();

    @Override
    public void run() {
        transferMoney(from, to, amount);
    }

    static class Account {
        public Account(int balance) {
            this.balance = balance;
        }

        int balance;
    }

    public void transferMoney(Account from, Account to, int amount) {
        int fromHashcode = System.identityHashCode(from);
        int toHashcode = System.identityHashCode(to);
        //先获得hashcode大的对象的锁
        if (fromHashcode > toHashcode) {
            synchronized (from) {
                synchronized (to) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (from.balance - amount < 0) {
                        System.out.println("余额不足，转账失败");
                    } else {
                        from.balance = from.balance - amount;
                        to.balance = to.balance + amount;
                        System.out.println("成功转账" + amount + "元");
                    }
                }
            }
        } else if (fromHashcode < toHashcode) {
            synchronized (to) {
                synchronized (from) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (from.balance - amount < 0) {
                        System.out.println("余额不足，转账失败");
                    } else {
                        from.balance = from.balance - amount;
                        to.balance = to.balance + amount;
                        System.out.println("成功转账" + amount + "元");
                    }
                }
            }
        } else {
            //当hashcode相等时在竞争另一把锁
            synchronized (lock) {
                synchronized (from) {
                    synchronized (to) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        Account account1 = new Account(500);
        Account account2 = new Account(500);
        new Thread(new FixedTransferMoney(account1,account2,random.nextInt(200))).start();
        new Thread(new FixedTransferMoney(account2,account1,random.nextInt(200))).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("account1的余额："+account1.balance);
        System.out.println("account2的余额："+account2.balance);
    }
}
