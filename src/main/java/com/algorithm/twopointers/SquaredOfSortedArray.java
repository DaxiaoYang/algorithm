package com.algorithm.twopointers;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/25
 */
public class SquaredOfSortedArray {


    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        int i = 0, j = len - 1;
        for (int k = len - 1; k >= 0; k--) {
            if (Math.abs(nums[i]) > Math.abs(nums[j])) {
                res[k] = nums[i] * nums[i];
                i++;
            } else {
                res[k] = nums[j] * nums[j];
                j--;
            }
        }
        return res;
    }
}
