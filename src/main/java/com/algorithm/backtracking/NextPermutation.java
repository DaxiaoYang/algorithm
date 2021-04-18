package com.algorithm.backtracking;

import java.util.Arrays;

public class NextPermutation {

    public static void main(String[] args) {
        int[] nums = {3,2,1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        // 从倒数第二位开始找
        int i = nums.length - 2;
        // 从右到左 找到第一个升序的二元组
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // i = -1 说明整个序列都是降序的 达到了全排列的最大的一组
        // i >= 0 表明如果能找到
        if (i >= 0) {
            // 找到右边的降序序列中 第一个大于nums[i]的数 与其交换
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        // 将[i + 1, len - 1] 这个降序序列的 变为升序序列（逆转整个序列即可）
        reverse(nums, i + 1, nums.length - 1);
    }

    private static void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
