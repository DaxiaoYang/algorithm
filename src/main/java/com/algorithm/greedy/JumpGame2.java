package com.algorithm.greedy;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/17
 */
public class JumpGame2 {

    public int jump(int[] nums) {
        // 当前范围末端
        int currEnd = 0;
        // 下一步的范围末端
        int nextEnd = 0;
        int step = 0;
        // 只遍历到倒数第二个 因为题目能保证有解 所以 所有范围连起来肯定能覆盖最后一个数
        for (int i = 0; i < nums.length - 1; i++) {
            // 在当前范围中更新下一个范围的末端
            nextEnd = Math.max(i + nums[i], nextEnd);
            // 到达当前范围末端
            if (i == currEnd) {
                // 更新范围末端
                currEnd = nextEnd;
                // 累积步数 即迈一步
                step++;
            }
        }
        return step;
    }
}
