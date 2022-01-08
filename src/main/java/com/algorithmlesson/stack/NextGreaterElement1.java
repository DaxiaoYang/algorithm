package com.algorithmlesson.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/19
 */
public class NextGreaterElement1 {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 单调栈里面存储的都是元素的下标
        // 单调栈应用场景都是 一个数组 找出当前元素的左边或者右边第一个比他大/小的元素
        Deque<Integer> stack = new ArrayDeque<>();
        int[] next = new int[nums2.length];
        // 先用单调栈解法 找出nums2每个元素的右边比他大的元素的下标
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                next[stack.pop()] = i;
            }
            stack.push(i);
        }
        // 反向索引
        Map<Integer, Integer> map = new HashMap<>(nums2.length);
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = next[map.get(nums1[i])] == 0 ? -1 : nums2[next[map.get(nums1[i])]];
        }
        return res;
    }
}
