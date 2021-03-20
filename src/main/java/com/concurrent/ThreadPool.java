package com.concurrent;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@FunctionalInterface
interface RejectPolicy<T> {
    void reject(ThreadPool.BlockingQueue<T> queue, T task);
}

public class ThreadPool {

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(1, 1000, TimeUnit.MILLISECONDS, 1, (queue, task) -> {
            // 死等
//            queue.put(task);
            // 放弃执行任务
//            System.out.println("放弃执行任务" + task);
            // 抛出异常
//            throw new RuntimeException("阻塞队列已满 任务无法执行" + task);
            // 带超时等待
//            System.out.println(queue.offer(task, 500, TimeUnit.MILLISECONDS));
            // 让调用者执行这个任务
            task.run();
        });
        for (int i = 0; i < 3; i++) {
            // 在lambda中使用变量的方法：将变量赋值给一个局部变量 这样这个局部变量就不会变
            int j = i;
            pool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(j);
            });
        }
    }

    // 阻塞队列
    private BlockingQueue<Runnable> taskQueue;
    // 工作线程集合
    private Set<Worker> workers = new HashSet<>();
    // 核心线程数
    private int coreSize;
    // 等待时间
    private long timeout;
    // 时间单位
    private TimeUnit timeUnit;
    // 拒绝策略
    private RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueSize, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        taskQueue = new BlockingQueue<>(queueSize);
        this.rejectPolicy = rejectPolicy;
    }

    public void execute(Runnable task) {
        // 如果当前线程数小于核心线程数 创建一个新的线程 执行任务
        // 如果当前线程数等于核心线程数 将任务放置于队列中
        // 加锁解决并发访问
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                worker.start();
                System.out.println("当前线程数小于核心线程数 创建新的线程" + worker + " 执行任务" + task);
                workers.add(worker);
            } else {
                // 当线程数达到核心线程数 且阻塞队列已满 会执行调用者传入的拒绝策略
//                taskQueue.put(task);
                taskQueue.tryPut(task, rejectPolicy);
            }
        }
    }

    private class Worker extends Thread {

        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {

            // 刚创建时 或者队列中还有任务时 如果没有任务核心线程就会一直阻塞在这里
//            while (task != null || (task = taskQueue.take()) != null) {
            while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 当前任务执行完了 如果不设为null 会一直执行
                    task = null;
                }
            }
            // 队列中没有任务了
            synchronized (workers) {
                System.out.println("移除线程" + this);
                workers.remove(this);
            }
        }
    }

    class BlockingQueue<T> {
        private int capacity;
        private Deque<T> tasks = new LinkedList<>();
        private Lock lock = new ReentrantLock();
        private Condition fullWaitSet = lock.newCondition();
        private Condition emptyWaitSet = lock.newCondition();

        public BlockingQueue(int capacity) {
            this.capacity = capacity;
        }


        // 带超时的获取
        public T poll(long timeout, TimeUnit timeUnit) {
            lock.lock();
            long nanos = timeUnit.toNanos(timeout);
            try {
                while (tasks.isEmpty()) {
                    try {
                        if (nanos <= 0) {
                            return null;
                        }
                        // 防止虚假唤醒 返回的时间是上次设置的时间与已经等待的时间的差值（>0时） <=0表明已经等到了时间
                        nanos = emptyWaitSet.awaitNanos(nanos);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                T ret = tasks.removeFirst();
                fullWaitSet.signal();
                return ret;
            } finally {
                lock.unlock();
            }
        }

        // 阻塞获取
        public T take() {
            lock.lock();
            try {
                while (tasks.isEmpty()) {
                    try {
                        emptyWaitSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                     }
                }
                T ret = tasks.removeFirst();
                fullWaitSet.signal();
                return ret;
            } finally {
                lock.unlock();
            }
        }

        // 阻塞放入
        public void put(T t) {
            lock.lock();
            try {
                while (tasks.size() == capacity) {
                    System.out.println("任务" + t + "等待加入阻塞队列中");
                    fullWaitSet.await();
                }
                System.out.println("将任务" + t + "放入阻塞队列中");
                tasks.addLast(t);
                emptyWaitSet.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void tryPut(T t, RejectPolicy<T> rejectPolicy) {
            lock.lock();
            try {
                if (tasks.size() == capacity) {
                    rejectPolicy.reject(this, t);
                } else {
                    System.out.println("将任务" + t + "放入阻塞队列中");
                    tasks.addLast(t);
                    emptyWaitSet.signal();
                }
            } finally {
                lock.unlock();
            }
        }

        // 带超时时间的放入
        public boolean offer(T task, long timeout, TimeUnit timeUnit) {
            lock.lock();
            long nanos = timeUnit.toNanos(timeout);
            try {
                while (tasks.size() == capacity) {
                    try {
                        if (nanos <= 0) {
                            return false;
                        }
                        System.out.println("task" + task + "等待加入队列");
                        nanos = fullWaitSet.awaitNanos(nanos);
                    } catch (Exception e) {
                       e.printStackTrace();
                    }
                }
                System.out.println("将任务" + task + "放入阻塞队列中");
                tasks.addLast(task);
                emptyWaitSet.signal();
                return true;
            } finally {
                lock.unlock();
            }
        }

        public int size() {
            lock.lock();
            try {
                return tasks.size();
            } finally {
                lock.unlock();
            }
        }


    }
}
