package com.leetcode.solution.java;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    int  i = 0;
    ReentrantLock reentrantLock = new ReentrantLock();

    public int incr(){

        int j= i;
        try{
            reentrantLock.lock();
            j++;
            return j;
        }finally {
            reentrantLock.unlock();
        }

    }



    public static void main(String[] args) {


        ExecutorService executorService = Executors.newFixedThreadPool(10);

        LockTest lockTest = new LockTest();

        CyclicBarrier barrier = new CyclicBarrier(5);

        for(int j =0 ;j<100;j++){
            for(int i =0 ;i<10;i++){
                executorService.execute(()->{
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
