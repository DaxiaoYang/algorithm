package com.algorithm.slidingwindow;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/17
 */
public class MinimumSizeSubArraySum {

    public int minSubArrayLen(int target, int[] nums) {
        // 当前选中的窗口范围为[i,j) sum表示当前窗口范围的和
        int i = 0, j = 0, sum = 0, minLen = Integer.MAX_VALUE;
        while (j < nums.length) {
            // 先加后移动指针 增大窗口范围
            sum += nums[j++];
            while (sum >= target) {
                minLen = Math.min(minLen, j - i);
                // 先减后移动指针 因为大了所以要缩小窗口范围
                sum -= nums[i++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
