package com.algorithm.array;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/29
 */
public class PivotIndex {


    public int pivotIndex(int[] nums) {
        int sum = 0;
        // 先求和 用于后面计算左半和的时候 通过减法获取右半和
        for (int num : nums) {
            sum += num;
        }
        // 表示[0,i]区间的和
        int leftSum = 0;
        // 表示[i,n-1]区间的和
        int rightSum = 0;
        for (int i = 0; i < nums.length; i++) {
            // sum - [0,i-1] = [i,n-1]
            rightSum = sum - leftSum;
            leftSum += nums[i];
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }
}
