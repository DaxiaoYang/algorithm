package com.concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/19
 */
public class BatchProcessDemo {

    private static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            threadPool.execute(() -> {
                while (true) {
                    List<Integer> integers = pollTasks();
                    // deal with tasks
                }
            });
        }
    }

    private static List<Integer> pollTasks() {
        LinkedList<Integer> taskToRun = new LinkedList<>();
        try {
            // 第一次阻塞
            Integer task = queue.take();
            while (task != null) {
                taskToRun.add(task);
                // 之后不阻塞
                task = queue.poll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return taskToRun;
    }
}
