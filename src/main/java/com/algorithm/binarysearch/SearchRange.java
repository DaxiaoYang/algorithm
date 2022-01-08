package com.algorithm.binarysearch;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/20
 */
public class SearchRange {

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int low = 0, high = nums.length - 1, mid;
        int leftIndex = -1;
        // 先找到左边界
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                if (mid == 0 || nums[mid - 1] < target) {
                    leftIndex = mid;
                    break;
                }
                high = mid - 1;
            }
        }
        if (leftIndex == -1) {
            return new int[]{-1, -1};
        }
        int rightIndex = -1;
        // 再找到右边界
        low = leftIndex; high = nums.length - 1;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                if (mid == nums.length - 1 || nums[mid + 1] > target) {
                    rightIndex = mid;
                    break;
                }
                low = mid + 1;
            }
        }
        return new int[]{leftIndex, rightIndex};
    }
}
