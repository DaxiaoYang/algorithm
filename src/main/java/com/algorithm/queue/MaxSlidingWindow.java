package com.algorithm.queue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/6
 */
public class MaxSlidingWindow {

    public static void main(String[] args) {
//        int[] nums = {1,3,-1,-3,5,3,6,7};
//        System.out.println(Arrays.toString(maxSlidingWindow(nums, 3)));

//        float a = 20_000_000.0f;
//        float b = 1.0f;
//        float c = a + b;
//        for (int i = 0; i < 10000; i++) {
//            a += b;
//        }
//        System.out.println(a);

        float sum = 0.0f;
        for (int i = 0; i < 20_000_000; i++) {
            sum += 1.0f;
        }
        System.out.println(sum);
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        // 单调递减队列 里面存储的是数组索引
        Deque<Integer> queue = new LinkedList<>();
        int[] res = new int[len - k + 1];
        // 遍历数组 当前的滑动窗口的范围是 [i - k + 1, i]
        for (int i = 0; i < len; i++) {
            // 队列头元素不在窗口范围内了 弹出
            if (!queue.isEmpty() && queue.peek() < i - k + 1) {
                queue.poll();
            }
            // 保持队列中的元素的是单调递减的
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.offer(i);
            // 得保证获得了一个滑动窗口长度了 再写数据
            if (i - k + 1 >= 0) {
                res[i - k + 1] = nums[queue.peek()];
            }
        }
        return res;
    }
}
