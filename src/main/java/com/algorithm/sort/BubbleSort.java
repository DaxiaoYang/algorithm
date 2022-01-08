package com.algorithm.sort;

import java.util.Arrays;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/15
 */
public class BubbleSort {


    public static void main(String[] args) {
        int[] nums = {4,5,6,3,2,1};
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void bubbleSort(int[] nums) {
        int swapCount = 0;
        int len = nums.length;
        boolean hasSwap;
        int temp;
        for (int i = 0; i < len - 1; i++) {
            hasSwap = false;
            for (int j = 0; j < len - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    hasSwap = true;
                    swapCount++;
                }
            }
            if (!hasSwap) {
                break;
            }
        }
        System.out.println("swapCount:" + swapCount);
    }
}
