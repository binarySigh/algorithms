package com.mjscode.algorithms.newCoder.huawei;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class HJ49_MultiThread {
    private volatile int count = 1;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    private Condition conditionD = lock.newCondition();

    // 主要：所有的都要用 while
    public void addA() {
        lock.lock();
        try {
            // 如果当前不是 1 ，则要阻塞等待
            while (count != 1) {
                conditionA.await();
            }
            count++;
            System.out.print("A");
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void addB() {
        lock.lock();
        try {
            // 如果当前不是 2 ，就要阻塞等待
            while (count != 2) {
                conditionB.await();
            }
            count++;
            System.out.print("B");
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void addC() {
        lock.lock();
        try {
            // 如果当前不是 3，则阻塞等待
            while (count != 3) {
                conditionC.await();
            }
            count++;
            System.out.print("C");
            conditionD.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void addD() {
        lock.lock();
        try {
            // 当前不是 4 ，则阻塞等待
            while (count != 4) {
                conditionD.await();
            }
            System.out.print("D");
            count = 1;
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            HJ49_MultiThread hj = new HJ49_MultiThread();
            CountDownLatch countDownLatch = new CountDownLatch(4);

            // 线程1
            new Thread(() -> {
                // A 线程负责add 10 次
                for (int i = 0; i < n; i++) {
                    hj.addA();
                }
                countDownLatch.countDown();
            }, "A").start();

            // 线程2
            new Thread(() -> {
                // A 线程负责add 10 次
                for (int i = 0; i < n; i++) {
                    hj.addB();
                }
                countDownLatch.countDown();
            }, "B").start();

            // 线程3
            new Thread(() -> {
                // A 线程负责add 10 次
                for (int i = 0; i < n; i++) {
                    hj.addC();
                }
                countDownLatch.countDown();
            }, "C").start();

            // 线程4
            new Thread(() -> {
                // A 线程负责add 10 次
                for (int i = 0; i < n; i++) {
                    hj.addD();
                }
                countDownLatch.countDown();
                System.out.println();
            }, "D").start();
            countDownLatch.await();
        }
    }
}
