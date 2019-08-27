package com.leetcode.solution.java;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.util.concurrent.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;

import javax.annotation.Nullable;
import javax.management.RuntimeErrorException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListenableFutureTest {

    public static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {

        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));


        BiMap<Integer, ListenableFuture<Integer>> biMap = HashBiMap.create();
        IntStream.range(0, 5).forEach(i -> {

            ListenableFuture<Integer> f = executorService.submit(() -> {

                int r = RandomUtils.nextInt(0, 5);
                LockSupport.parkUntil(System.currentTimeMillis() + r * 1000);
                System.out.println("test i=" + i + ",Thread=" + Thread.currentThread() + ",r=" + r);

                if (i == 2) {
                    throw new RuntimeException("test exception,r=" + r);
                }
                return i;
            });
            biMap.put(i, f);
        });


//        blockUtilComplete(biMap.values(),(r,f)->{
//            System.out.println("f,onSuccess ,Thread=" + Thread.currentThread());
//            biMap.inverse().remove(f);
//        },(t,f)->{
//            System.out.println("f,exception ,Thread=" + Thread.currentThread());
//
//        });

        try {
            blockUtilSuccess(biMap.values());
        } catch (Throwable throwable) {
            System.out.println("result errror ,e" + throwable);
        }

        System.out.println("size=" + biMap.size());


//        Futures.addCallback(Futures.whenAllComplete(futures).call(() -> {
//            System.out.println("whenAllComplete ,Thread=" + Thread.currentThread());
//
//
//            return null;
//        }, MoreExecutors.directExecutor()), new FutureCallback<Boolean>() {
//            @Override
//            public void onSuccess(@Nullable Boolean result) {
//                System.out.println("onSuccess ,Thread=" + Thread.currentThread());
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                System.out.println("onFailure ,Thread=" + Thread.currentThread());
//            }
//        }, MoreExecutors.directExecutor());

        System.out.println("all done ,Thread=" + Thread.currentThread());
    }


    public static <T> void blockUtilSuccess(final Collection<ListenableFuture<T>> futures) throws Exception {
        final Thread thread = Thread.currentThread();
        ListenableFuture<Void> f = Futures.whenAllComplete(futures).call(() -> null, MoreExecutors.directExecutor());
        AtomicReference<Exception> atomicReference = new AtomicReference<>();
        Futures.addCallback(f, new FutureCallback<Void>() {
            @Override
            public void onSuccess(@Nullable Void result) {
                LockSupport.unpark(thread);
            }

            @Override
            public void onFailure(Throwable t) {
                try {
                    atomicReference.set(t instanceof Exception ? (Exception) t : new RuntimeException(t.getMessage()));
                } finally {
                    LockSupport.unpark(thread);
                }

            }
        }, MoreExecutors.directExecutor());
        LockSupport.park();
        if (atomicReference.get() != null) {
            throw atomicReference.get();
        }
    }


    public static <T> void blockUtilComplete(final Collection<ListenableFuture<T>> futures,
                                             final BiConsumer<T, ListenableFuture<T>> successCall,
                                             final BiConsumer<Throwable, ListenableFuture<T>> exceptionCall) throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(futures.size());
        for (final Iterator<ListenableFuture<T>> iterator = futures.iterator(); iterator.hasNext(); ) {
            final ListenableFuture<T> future = iterator.next();
            Futures.addCallback(future, new FutureCallback<T>() {
                @Override
                public void onSuccess(@Nullable final T result) {
                    try {
                        successCall.accept(result, future);
                    } finally {
                        countDownLatch.countDown();
                    }
                }

                @Override
                public void onFailure(final Throwable t) {
                    try {
                        exceptionCall.accept(t, future);
                    } finally {
                        countDownLatch.countDown();
                    }

                }
            }, MoreExecutors.directExecutor());
        }
        countDownLatch.await();
    }
}
