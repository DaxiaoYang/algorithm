package com.algorithm.binarysearch;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/19
 */
public class SearchInsert {

    public int searchInsert(int[] nums, int target) {
        // 处理边界条件
        if (target < nums[0]) {
            return 0;
        }
        int len = nums.length;
        if (target > nums[len - 1]) {
            return len;
        }
        // 在有序数组中找到第一个>= target 的元素
        int low = 0, high = len - 1, mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                if (mid == 0 || nums[mid - 1] < target) {
                    return mid;
                }
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 4种情况：
     * 1.目标在数组中
     * 2.目标需要插入数组中
     * 3.目标小于所有元素
     * 4.目标大于所有元素
     */
    public int searchInsert2(int[] nums, int target) {
        int low = 0, high = nums.length - 1, mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return high + 1;
    }
}
