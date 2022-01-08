package com.algorithm.sort;

import java.util.Arrays;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/15
 */
public class InsertionSort {

    public static void main(String[] args) {
            int[] nums = {4,5,6,3,2,1};
//        int[] nums = {1,3,4,2};
        insertionSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void insertionSort(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int temp = nums[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (nums[j] > temp) {
                    nums[j + 1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j + 1] = temp;
        }
    }
}
