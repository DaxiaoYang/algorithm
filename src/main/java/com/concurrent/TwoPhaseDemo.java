package com.concurrent;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/19
 */
public class TwoPhaseDemo {

    public static void main(String[] args) throws InterruptedException {
        ReportProxy reportProxy = new ReportProxy();
        reportProxy.start();
        Thread.sleep(10000);
        reportProxy.stop();
    }

    private static class ReportProxy {

        private Thread reportThread;

        private volatile boolean stop;

        private boolean started;

        public synchronized void start() {
            if (started) {
                return;
            }
            reportThread = new Thread(() -> {
                while (!stop) {
                    // 执行业务操作
                    System.out.println(Thread.currentThread().getName() + " doing sth.");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            reportThread.start();
            started = true;
        }

        public synchronized void stop() {
            if (reportThread != null) {
                stop = true;
                reportThread.interrupt();
                started = false;
            }
        }
    }
}
