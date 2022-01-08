package com.algorithm.slidingwindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/11/23
 */
public class MaxSlidingWindow {

    public static void main(String[] args) {
       int[] nums =  {1,3,-1,-3,5,3,6,7};
        int[] ints = maxSlidingWindow(nums, 3);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue queue = new MonotonicQueue();
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        // res[i] 表示[i, i + k - 1]这个窗口内的最大值
        int[] res = new int[nums.length - k + 1];
        res[0] = queue.peek();
        for (int i = k; i < nums.length; i++) {
            queue.poll(nums[i - k]);
            queue.offer(nums[i]);
            res[i - k + 1] = queue.peek();
        }
        return res;
    }

    /**
     * 元素范围为滑动窗口
     * 单调队列 队头 -> 队尾 单调递减
     */
    private static class MonotonicQueue {

        private Deque<Integer> queue = new LinkedList<>();

        /**
         * 在队列加入一个元素 维持递减特性
         * @param num
         */
        public void offer(int num) {
            while (!queue.isEmpty() && queue.peekLast() < num) {
                queue.pollLast();
            }
            queue.offer(num);
        }

        /**
         * 如果从滑动窗口中移除的元素为最大的元素 则移除
         * @param num
         */
        public void poll(int num) {
            if (!queue.isEmpty() && queue.peekFirst() == num) {
                queue.pollFirst();
            }
        }

        /**
         * 返回当前滑动窗口的最大元素
         * @return
         */
        public int peek() {
            return queue.peekFirst();
        }
    }
}
