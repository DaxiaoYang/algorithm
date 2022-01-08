package com.algorithmlesson.binarysearch;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/28
 */
public class FindMin {

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(findMin(nums));
    }

    public static int findMin(int[] nums) {
        int len = nums.length;
        if (len == 1 || nums[0] < nums[len - 1]) {
            return nums[0];
        }
        int low = 0, high = len - 1, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (mid != 0 && nums[mid - 1] > nums[mid]) {
                return mid;
            }
            if (high - low == 1) {
                if (nums[low] < nums[high]) {
                    return low;
                } else {
                    return high;
                }
            }
            if (nums[low] > nums[mid]) {
                high = mid - 1;
            }
            if (nums[high] < nums[mid]) {
                low = mid + 1;
            }
        }
        return -1;
    }
}
