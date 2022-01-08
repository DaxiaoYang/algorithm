package com.algorithmlesson.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/19
 */
public class NextGreaterElement2 {

    public int[] nextGreaterElements(int[] nums) {
        // 存储的都是数组下标
        Deque<Integer> stack = new ArrayDeque<>();
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, -1);
        // 用 i % len 模拟遍历两次数组 相当胡逻辑上往原始数组后复制了一份数组
        for (int i = 0; i < 2 * len; i++) {
            while (!stack.isEmpty() && nums[i % len] > nums[stack.peek()]) {
                res[stack.pop()] = nums[i % len];
            }
            stack.push(i % len);
        }
        return res;
    }
}
