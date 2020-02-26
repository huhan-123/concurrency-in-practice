package jmm;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: huhan
 * @Date 2020/2/23 3:20 下午
 * @Description 演示可见性问题
 * @Verion 1.0
 */
public class FieldVisibility {
    int a = 1;
    int b = 2;


    public static void main(String[] args) {
        while (true) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            FieldVisibility test = new FieldVisibility();
            Thread thread1 = new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.change();
            });
            Thread thread2 = new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.print();
            });
            thread1.start();
            thread2.start();
            countDownLatch.countDown();
        }
    }

    private void print() {
        //有四种情况（3，3），（3，2），（1，2），（1，3）
        //当出现a=1,b=3时，说明a已经被线程1修改成了3，但是并没有冲刷到主内存，b的值被冲刷到了主内存
        System.out.println("a=" + a + ",b=" + b);
    }

    private void change() {
        a = 3;
        //如果b用volatile修饰，那么b=a前面的操作a=3也具有可见性（b和a的值都会被冲刷到主内存）
        b = a;
    }

}
