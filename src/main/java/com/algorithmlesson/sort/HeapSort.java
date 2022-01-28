package com.algorithmlesson.sort;

import java.util.Arrays;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/27
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] nums = {0, 1, 6, 2, 4, 3};
        heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void heapSort(int[] nums) {
        buildHeap(nums, nums.length - 1);
        heapSort(nums, nums.length - 1);
    }

    private static void buildHeap(int[] nums, int n) {
        for (int i = n / 2; i >= 1; i--) {
            heapify(nums, n, i);
        }
    }

    private static void heapSort(int[] nums, int n) {
        while (n > 1) {
            swap(nums, 1, n);
            n--;
            heapify(nums, n, 1);
        }
    }

    private static void heapify(int[] nums, int n, int i) {
        int maxPos;
        while (true) {
            maxPos = i;
            if (2 * i <= n && nums[2 * i] > nums[i]) {
                maxPos = 2 * i;
            }
            if (2 * i + 1 <= n && nums[2 * i + 1] > nums[maxPos]) {
                maxPos = 2 * i + 1;
            }
            if (maxPos == i) {
                break;
            }
            swap(nums, i, maxPos);
            i = maxPos;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
