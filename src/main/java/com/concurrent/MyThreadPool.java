package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/10
 */
public class MyThreadPool implements Executor {

    private final BlockingQueue<Runnable> queue;

    private final int threadNum;

    private List<Thread> consumeThreads;

    public MyThreadPool(BlockingQueue<Runnable> queue, int threadNum) {
        this.queue = queue;
        this.threadNum = threadNum;
        consumeThreads = new ArrayList<>(threadNum);
        for (int i = 0; i < threadNum; i++) {
            ConsumeThread consumeThread = new ConsumeThread();
            consumeThread.start();
            consumeThreads.add(consumeThread);
        }
    }

    @Override
    public void execute(Runnable command) {
        queue.add(command);
    }


    private class ConsumeThread extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    Runnable task = queue.take();
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Runnable> runnables = new LinkedBlockingQueue<>(100);
        MyThreadPool myThreadPool = new MyThreadPool(runnables, 4);
        myThreadPool.execute(() -> System.out.println("haha"));
    }
}
