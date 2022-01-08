package com.algorithmlesson.queue;

import java.util.*;

/**
 * @ description: 
 * @ author: daxiao
 * @ date: 2021/12/20 
 */
public class SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        System.out.println(maxSlidingWindow(nums, 3));
        Map<String, List<String>> map = new HashMap<>();
        Collection<List<String>> values = map.values();
    }


    public static int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue queue = new MonotonicQueue();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        res[0] = queue.peek();
        for (int i = k; i < nums.length; i++) {
            queue.poll(nums[i - k]);
            queue.offer(nums[i]);
            res[i - k + 1] = queue.peek();
        }
        return res;
    }


    private static class MonotonicQueue {

        private Deque<Integer> queue = new LinkedList<>();

        public void offer(int val) {
            while (!queue.isEmpty() && queue.peekLast() < val) {
                queue.pollLast();
            }
            queue.offer(val);
        }

        public int peek() {
            return queue.peek();
        }

        public void poll(int val) {
            if (val == peek()) {
                queue.poll();
            }
        }
    }
}
