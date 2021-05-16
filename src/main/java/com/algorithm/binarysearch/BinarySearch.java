package com.algorithm.binarysearch;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/15
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {1,2,2,2,3};
//        System.out.println(search(nums, 1));

        System.out.println(searchFirst(nums, 2));
        System.out.println(searchLast(nums, 2));
        System.out.println(searchFirstBiggerAndEquals(nums, 2));
        System.out.println(seachLastLessAndEquals(nums, 2));
    }

    /**
     * 在不存在重复元素的有序数组中查找一个元素
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1, mid;
        // low == high也是合法的
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 查找第一个等于给定值的元素的下标
     * @param nums 可能存在重复元素
     * @param target
     * @return
     */
    public static int searchFirst(int[] nums, int target) {
        int low = 0, high = nums.length - 1, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                // 判断他是不是第一个等于的
                if (mid == 0 || nums[mid - 1] != target) {
                    return mid;
                }
                // 不是 那所求值在mid的左边
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个等于给定值的元素的下标
     * @param nums 可能存在重复元素
     * @param target
     * @return
     */
    public static int searchLast(int[] nums, int target) {
        int low = 0, high = nums.length - 1, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                // 判断他是不是最后一个等于的 如果是就返回
                if (mid == nums.length - 1 || target != nums[mid + 1]) {
                    return mid;
                }
                // 说明所求值在mid右边
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 找到第一个大于等于给定值的元素的下标
     * @param nums
     * @param target
     * @return
     */
    public static int searchFirstBiggerAndEquals(int[] nums, int target) {
        int low = 0, high = nums.length - 1, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                // 找到一个>=的 看看是否是第一个
                if (mid == 0 || nums[mid - 1] < target) {
                    return mid;
                }
                // 因为想找第一个 所以往左边区间看
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 找到最后一个小于等于给定值的元素的下标
     * @param nums
     * @param target
     * @return
     */
    public static int seachLastLessAndEquals(int[] nums, int target) {
        int low = 0, high = nums.length - 1, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (nums[mid] <= target) {
                // 判断是否是最后一个
                if (mid == nums.length - 1 || nums[mid + 1] > target) {
                    return mid;
                }
                // 因为想找最后一个 所以往右边区间看
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
