package com.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * 求连续子数组的最大和
 */
public class MaxSubArray {

    public static void main(String[] args) {
        int[] nums = {31,-41,59,26,-53,58,97,-93,-23,84};
        System.out.println(getMaxSubArray1(nums));
        System.out.println(getMaxSubArray2(nums));
        System.out.println(getMaxSubArray3(nums));
        System.out.println(getMaxSubArray4(nums));
        System.out.println(getMaxSubArray5(nums));
    }


    // O(n^3)
    public static int getMaxSubArray1(int[] nums) {
        // 枚举所有的可能(i,j)
        int max = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int sum = 0;
                // 对每个可能 都计算其子数组的和
                for (int k = i; k <= j; k++) {
                   sum += nums[k];
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    // O(n^2) 平方级的算法 都是利用之前计算的数据来推导出每个可能的子数组的和 而不是进行重复计算
    public static int getMaxSubArray2(int[] nums) {
        int max = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // sum: (i, j)的和
            int sum = 0;
            // 利用的递推关系： sum(i, j) = nums[j] + sum(i, j - 1)
            for (int j = i; j < len; j++) {
                sum += nums[j];
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    // O(n^2)
    public static int getMaxSubArray3(int[] nums) {
        int max = 0;
        int len = nums.length;
        // 对数据进行预处理
        int[] acc = Arrays.copyOf(nums, len);
        for (int i = 1; i < len; i++) {
            acc[i] = acc[i - 1] + acc[i];
        }
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                // sum(i, j) = acc[j] - acc[i - 1]
                int dist;
                if (i == 0) {
                    dist = acc[j];
                } else {
                    dist = acc[j] - acc[i - 1];
                }
                max = Math.max(max, dist);
            }
        }
        return max;
    }

    // O(nlogn)
    public static int getMaxSubArray4(int[] nums) {
        return getMaxSubArray4(nums, 0, nums.length - 1);
    }

    private static int getMaxSubArray4(int[] nums, int low, int high) {
        // 子数组中无元素
        if (low > high) {
            return 0;
        }
        // 子数组中只有一个元素 若为负数则舍去 因为负数反而会起反作用
        if (low == high) {
            return Math.max(nums[low], 0);
        }
        // 最大连续子数组 会从(low, mid) (mid + 1, high) 以及跨越mid的子数组中产生
        // 计算跨越mid的最大连续子数组：包含mid的左半边最优数组 包含mid + 1的右半边最优数组
        int sum = 0, leftMax = 0, rightMax = 0, mid = low + (high - low) / 2;
        for (int i = mid; i >= low; i--) {
            // sum: maxSubSum(i, mid)
            sum += nums[i];
            leftMax = Math.max(sum, leftMax);
        }
        sum = 0;
        for (int i = mid + 1; i <= high; i++) {
            // sum: maxSubSum(mid + 1, high)
            sum += nums[i];
            rightMax = Math.max(sum, rightMax);
        }
        // 最大和在这三个中产生
        return Math.max(leftMax + rightMax, Math.max(getMaxSubArray4(nums, low, mid), getMaxSubArray4(nums, mid + 1, high)));
    }

    // O(n) 动态规划
    public static int getMaxSubArray5(int[] nums) {
        // sum表示[0,i]这个范围内的最大连续子数组的和
        int sum = 0, maxSum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 状态转移 sum(0, i) = nums[i] + max(sum(0, j -1), 0) 如果之前的和小于0
            // 那么加上反而会使总和变小 于是舍弃 从当前i重新开始计算
            sum = nums[i] + Math.max(sum, 0);
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }
}
