package com.algorithmlesson.sort;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/3
 */
public class ReversePair {


    private int reverseCount;

    /**
     * 排序就是一个不断降低序列中的逆序度的过程
     * 要计算逆序对 只需要在排序中计算个数即可
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        reverseCount = 0;
        mergeSort(nums, 0, nums.length - 1);
        return reverseCount;
    }

    private void mergeSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(nums, low, mid);
        mergeSort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    private void merge(int[] nums, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low, j = mid + 1, k = 0;
        while (i <= mid && j <= high) {
            // 后面的数字要挪到前面来 就会降低逆序度
            if (nums[j] < nums[i]) {
                reverseCount += mid - i + 1;
                temp[k++] = nums[j++];
            } else {
                temp[k++] = nums[i++];
            }
        }
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= high) {
            temp[k++] = nums[j++];
        }
        for (i = low; i <= high; i++) {
            nums[i] = temp[i - low];
        }
    }
}
