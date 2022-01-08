package com.algorithm.array;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/24
 */
public class KthLargetElementInArray {

    /**
     * O(n)时间复杂度内找到第K大的元素
     * quick select
     */
    public int findKthLargest(int[] nums, int k) {
        int target = nums.length - k;
        int pivotIndex;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            // nums[pivotIndex] 已经在其最终位置上了 
            pivotIndex = partition(nums, start, end);
            if (pivotIndex < target) {
                start = pivotIndex + 1;
            } else if (pivotIndex > target) {
                end = pivotIndex - 1;
            } else {
                return nums[pivotIndex];
            }
        }
        return -1;
    }

    private int partition(int[] nums, int start, int end) {
        int randomIndex = start + ThreadLocalRandom.current().nextInt(end - start + 1);
        swap(nums, randomIndex, end);
        int i = start;
        for (int j = start; j < end; j++) {
            if (nums[j] < nums[end]) {
                swap(nums, i++, j);
            }
        }
        swap(nums, i, end);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
