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

    public int minSubArrayLen2(int target, int[] nums) {
        int i = 0, j = 0, minLen = Integer.MAX_VALUE, sum = nums[0];
        // [i,j]窗口范围 sum为窗口范围内的子数组的和
        while (i < nums.length) {
            if (sum < target) {
                // 当j == len - 1时 len - 1已经被计算过 仍然小于目标 没有继续需要添加的元素了 所以退出
                if (j == nums.length - 1) {
                    break;
                }
                sum += nums[++j];
            } else {
                minLen = Math.min(minLen, j - i + 1);
                sum -= nums[i++];
            }
        }
        // 未找到一个子数组 minLen仍为原值
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
