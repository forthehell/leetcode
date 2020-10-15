package com.leetcode.solution.java;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    Inte i = new Inte();
    ReentrantLock reentrantLock = new ReentrantLock();

    public int incr() {

        Inte j = i;
        try {
            reentrantLock.lock();
            System.out.println("j");
            return j.incr();
        } finally {
            reentrantLock.unlock();
        }

    }

    class Inte {
        int i;

        int incr() {
            return ++i;
        }
    }


    public static void main(String[] args) {


        ExecutorService executorService = Executors.newFixedThreadPool(10);

        LockTest lockTest = new LockTest();

        CyclicBarrier barrier = new CyclicBarrier(5);

        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 10; i++) {
                executorService.execute(() -> {
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(lockTest.incr());
                });
            }

        }


    }
}
