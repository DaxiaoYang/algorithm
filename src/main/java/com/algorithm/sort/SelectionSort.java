package com.algorithm.sort;

import java.util.Arrays;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/16
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] nums = {4,5,6,3,2,1};
        selectionSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void selectionSort(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = temp;
            }
        }
    }
}
