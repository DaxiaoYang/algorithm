package com.algorithm.greedy;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/16
 */
public class JumpGame {

    public boolean canJump(int[] nums) {
        // 只有一个 肯定能到达
        if (nums.length == 1) {
            return true;
        }
        // 当前最远能覆盖的范围
        int coverRange = nums[0];
        for (int i = 0; i <= coverRange; i++) {
            // 在当前能覆盖的范围内 更新最远能覆盖的范围
            coverRange = Math.max(coverRange, i + nums[i]);
            // 如果最远可以超过数组末 则说明能到达
            if (coverRange >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}
