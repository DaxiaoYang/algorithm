package com.algorithmlesson.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/8
 */
public class SmallestK {


    public int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[]{};
        }
        // 分区点为 k - 1时 前k-1个元素都小于[k-1] 所以就找到了最小的k个数
        List<Integer> res = smallestK(arr, k - 1, 0, arr.length - 1);
        int[] ret = new int[res.size()];
        int len = 0;
        for (int num : res) {
            ret[len++] = num;
        }
        return ret;
    }

    /**
     * 快排分区的思想 实现找到最小的K个数
     * @param nums
     * @param position
     * @param low
     * @param high
     * @return
     */
    private List<Integer> smallestK(int[] nums, int position, int low, int high) {
        if (low > high) {
            return new LinkedList<>();
        }
        int pivot = partition(nums, low, high);
        if (pivot == position) {
            List<Integer> res = new ArrayList<>(position + 1);
            for (int i = 0; i <= position; i++) {
                res.add(nums[i]);
            }
            return res;
        } else if (pivot < position) {
            return smallestK(nums, position, pivot + 1, high);
        } else {
            return smallestK(nums, position, low, pivot - 1);
        }
    }

    private int partition(int[] nums, int low, int high) {
        int j = low;
        for (int i = low; i < high; i++) {
            if (nums[i] < nums[high]) {
                swap(nums, i, j);
                j++;
            }
        }
        swap(nums, j, high);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
