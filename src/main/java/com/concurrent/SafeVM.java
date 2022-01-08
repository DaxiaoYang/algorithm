package com.concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/13
 */
public class SafeVM {

    private static final AtomicReference<VMRange> reference = new AtomicReference<>(new VMRange(0, 0));

    private static class VMRange {

        private final int lower;

        private final int upper;

        public VMRange(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }
    }

    public static void updateUpper(int upper) {
        while (true) {
            VMRange currentRange = reference.get();
            if (currentRange.lower > upper) {
                throw new IllegalArgumentException();
            }
            VMRange newVmRange = new VMRange(currentRange.lower, upper);
            if (reference.compareAndSet(currentRange, newVmRange)) {
                return;
            }
        }
    }
}
