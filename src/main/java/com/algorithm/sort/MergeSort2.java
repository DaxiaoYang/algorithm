package com.algorithm.sort;

import java.util.Arrays;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/16
 */
public class MergeSort2 {

    public static void main(String[] args) {
        int[] nums = {4,5,6,3,2,1};
        mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }


    public static void mergeSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    private static void mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        merge(nums, start, mid, end);
    }

    /**
     * 将两个有序子数组 [start,mid] [mid+1,end]合并成一个
     */
    private static void merge(int[] nums, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;
        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        // 将剩下的一个数组的数据放到新的数组中
        int remainStart = i;
        int remainEnd = mid;
        if (j <= end) {
            remainStart = j;
            remainEnd = end;
        }
        while (remainStart <= remainEnd) {
            temp[k++] = nums[remainStart++];
        }
        System.arraycopy(temp, 0, nums, start, temp.length);
    }
}
