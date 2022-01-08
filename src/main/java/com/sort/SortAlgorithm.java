package com.sort;


import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/2
 */
public class SortAlgorithm {

    public static void main(String[] args) {
        int[] nums = {3,1,4,2};
        CompletableFuture.runAsync(() -> System.out.println("123"));
//        insertionSort(nums);
//        bubbleSort(nums);
//        selectionSort(nums);
//        mergeSort(nums);
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 插入排序
     */
    public static void insertionSort(int[] nums) {
        int len = nums.length;
        int temp;
        for (int i = 1; i < len; i++) {
            temp = nums[i];
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

    public static void bubbleSort(int[] nums) {
        int len = nums.length;
        boolean hasSwap;
        for (int i = 0; i < len - 1; i++) {
            hasSwap = false;
            for (int j = 0; j < len - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    hasSwap = true;
                }
            }
            if (!hasSwap) {
                break;
            }
        }
    }

    public static void selectionSort(int[] nums) {
        int len = nums.length;
        int min;
        int minIndex;
        for (int i = 0; i < len - 1; i++) {
            min = nums[i];
            minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < min) {
                    min = nums[j];
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
    }

    public static void mergeSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    private static void mergeSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(nums, low, mid);
        mergeSort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    private static void merge(int[] nums, int low, int mid, int high) {
        int i = low, j = mid + 1, k = 0;
        int[] temp = new int[high - low + 1];
        while (i <= mid && j <= high) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
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

    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = partition(nums, low, high);
        quickSort(nums, low, pivot - 1);
        quickSort(nums, pivot + 1, high);
    }

    private static int partition(int[] nums, int low, int high) {
        int j = low;
        for (int i = low; i < high; i++) {
            if (nums[i] < nums[high]) {
                swap(nums, j++, i);
            }
        }
        swap(nums, j, high);
        return j;
    }

    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
