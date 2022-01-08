package com.concurrent;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.*;


/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/19
 */
public class ProducerConsumer {

    private static class Logger {

        private static BlockingQueue<LogMsg> queue = new LinkedBlockingQueue<>();

        private static ExecutorService threadPool;

        public static void start() throws IOException {
            threadPool = Executors.newSingleThreadExecutor();
            threadPool.execute(() -> {
                try (FileWriter writer = new FileWriter("")) {
                    int currBatch = 0;
                    long lastFlush = System.currentTimeMillis();
                    while (true) {
                        LogMsg logMsg = queue.poll(5, TimeUnit.SECONDS);
                        if (logMsg != null) {
                            writer.write(logMsg.msg);
                            currBatch++;
                        }
                        if (currBatch == 0) {
                            continue;
                        }
                        if (logMsg != null && logMsg.level == Level.ERROR
                                || currBatch == 500
                                || System.currentTimeMillis() - lastFlush >= 5000) {
                            writer.flush();
                            currBatch = 0;
                            lastFlush = System.currentTimeMillis();
                        }
                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            });
        }


        public static void log(String msg, Level level) {
            queue.add(new LogMsg(level, msg));
        }

        public static class LogMsg {

            Level level;

            String msg;

            public LogMsg(Level level, String msg) {
                this.level = level;
                this.msg = msg;
            }
        }

        public enum Level {
            INFO,
            ERROR;
        }
    }
}
