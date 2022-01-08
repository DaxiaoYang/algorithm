package com.algorithm.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/18
 */
public class QuickSort2 {

    public static void main(String[] args) {
        int[] nums = {4,5,6,3,2,1};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = partition(nums, start, end);
        quickSort(nums, start, pivot - 1);
        quickSort(nums, pivot + 1, end);
    }

    private static int partition(int[] nums, int start, int end) {
        int randomIndex = start + ThreadLocalRandom.current().nextInt(end - start + 1);
        swap(nums, end, randomIndex);
        int i = start, j = start;
        for (; i < end; i++) {
            if (nums[i] < nums[end]) {
                swap(nums, i, j++);
            }
        }
        swap(nums, j, end);
        return j;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
