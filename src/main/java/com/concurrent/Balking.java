package com.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/15
 */
public class Balking {

    private static class AutoSaveEditor {

        private boolean changed;

        private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        public void startCheckTask() {
            executorService.schedule(this::autoSave, 5, TimeUnit.SECONDS);
        }

        public void edit() {
            // 进行文本修改

            // 将并发处理逻辑与业务逻辑分开
            change();
        }

        public void change() {
            synchronized (this) {
                changed = true;
            }
        }

        public void autoSave() {
            synchronized (this) {
                if (!changed) {
                    return;
                }
            }
            changed = false;
            // save to local disk
        }
    }
}
