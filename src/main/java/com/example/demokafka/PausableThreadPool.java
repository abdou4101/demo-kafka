package com.example.demokafka;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PausableThreadPool {
    private final ExecutorService executorService;
    private final Lock pauseLock = new ReentrantLock(true);
    private final Condition pausedCondition = pauseLock.newCondition();
    private volatile boolean isPaused = false;

    public PausableThreadPool(int poolSize) {
        executorService = Executors.newFixedThreadPool(poolSize);
    }

    public void submit(Runnable task) {
        executorService.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                pauseLock.lock();
                try {
                    // If the thread pool is paused, wait until resumed
                    while (isPaused) {
                        pausedCondition.await();
                    }
                    // Run the actual task here
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Preserve interrupt status
                    break;
                } finally {
                    pauseLock.unlock();
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        Thread.currentThread().interrupt();
//                    }
                }
            }
        });
    }

    public void pause() {
        pauseLock.lock();
        try {
            isPaused = true;
            System.out.println("Paused");
        } finally {
            pauseLock.unlock();
        }
    }

    public void resume() {
        pauseLock.lock();
        try {
            isPaused = false;
            pausedCondition.signalAll();  // Wake up all paused threads
            System.out.println("Resumed");
        } finally {
            pauseLock.unlock();
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        PausableThreadPool threadPool = new PausableThreadPool(10);

        // Submit a sample task
        for (int i = 1; i <= 10; i++) {
            final int taskNum = i;
            threadPool.submit(() -> {
                    System.out.println("Executing task " + taskNum + " Thread : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Simulate pausing and resuming
        Thread.sleep(6000);
        System.out.println("Pausing...");
        threadPool.pause();

        Thread.sleep(4000);
        System.out.println("Resuming...");
        threadPool.resume();

        threadPool.shutdown();
    }
}