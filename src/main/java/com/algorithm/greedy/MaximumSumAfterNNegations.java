package com.algorithm.greedy;

import java.util.stream.IntStream;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/18
 */
public class MaximumSumAfterNNegations {


    /**
     * 两次贪心
     * 1. 找出所有当前绝对值最大的负数 进行反转 局部最优 —> 全局最优
     * 2. 如果还有反转机会 则说明所有负数已经全部反转完了 问题转化为在正数序列中进行反转 此时选择最小值进行反复横跳
     * @param nums
     * @param k 可以反转的次数
     * @return
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        // 按绝对值 从大到小排序
        nums = IntStream.of(nums).boxed()
                .sorted((a, b) -> Math.abs(b) - Math.abs(a))
                .mapToInt(Integer::intValue)
                .toArray();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (k > 0 && nums[i] < 0) {
                nums[i] = -nums[i];
                k--;
            }
        }
        if (k % 2 == 1) {
            nums[len - 1] = -nums[len - 1];
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}
