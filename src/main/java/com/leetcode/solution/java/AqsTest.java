package com.leetcode.solution.java;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

public class AqsTest {

    Semaphore semaphore = new Semaphore(1);
    long start = System.currentTimeMillis();

    public void test() {
        boolean tryed = false;

        try {

            while (!tryed) {
                tryed =semaphore.tryAcquire(1,TimeUnit.SECONDS);
            }

            System.out.println("semaphore =" + semaphore.getQueueLength() + ";" + semaphore.availablePermits() + ";" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            LockSupport.parkUntil(System.currentTimeMillis() + 1000 * 5);
            if (tryed) {
                semaphore.release();
            }
        }


    }


    public static void main(String[] args) {

        AqsTest test = new AqsTest();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(() -> {
            for (; ; ) {
                test.test();
//                LockSupport.parkUntil(System.currentTimeMillis() + 1000 * 5);
            }
        });

        executorService.execute(() -> {
            for (; ; ) {
                test.test();
//                LockSupport.parkUntil(System.currentTimeMillis() + 1000 * 5);
            }
        });

        executorService.execute(() -> {
            for (; ; ) {
                test.test();
//                LockSupport.parkUntil(System.currentTimeMillis() + 1000 * 5);
            }
        });
        for (; ; ) {

        }
    }

}
