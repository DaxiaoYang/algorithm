package com.algorithm.search;

public class BinarySearch {
    public static void main(String[] args) {
//        int[] a = {1,3,4,5,6,8,8,8,11,18};
//        System.out.println(findFirstEqual(a, 8));
//        System.out.println(findLastEqual(a, 8));
//        System.out.println(findFirstGreaterAndEqual(a, 8));
//        System.out.println(findLastLessAndEqual(a, 8));
        int[] b = {4,5,6,7,0,1,2};
        System.out.println(findInRotatedArray(b, 0));
    }
    // 查找第一个值等于给定值的元素下标
    public static int findFirstEqual(int[] a, int target) {
        int low = 0, high = a.length - 1, mid;
        while (low <= high) {
            // 加法运算比位移运算优先级要高
            mid = low + ((high - low) >> 1);
            if (a[mid] > target) {
                high = mid - 1;
            } else if (a[mid] < target) {
                low = mid + 1;
            } else {
                if (mid == 0 || a[mid - 1] != target) {
                    return mid;
                }
                high = mid - 1;
            }
        }
        return -1;
    }

    // 查找最后一个值等于给定值的元素下标
    public static int findLastEqual(int[] a, int target) {
        int len = a.length;
        int low = 0, high = len - 1, mid;
        while (low <= high) {
            // 加法运算比位移运算优先级要高
            mid = low + ((high - low) >> 1);
            if (a[mid] > target) {
                high = mid - 1;
            } else if (a[mid] < target) {
                low = mid + 1;
            } else {
                if (mid == len - 1 || a[mid + 1] != target) {
                    return mid;
                }
                low = mid + 1;
            }
        }
        return -1;
    }

    // 查找第一个大于等于给定值的元素下标
    public static int findFirstGreaterAndEqual(int[] a, int target) {
        int len = a.length;
        int low = 0, high = len - 1, mid;
        while (low <= high) {
            // 加法运算比位移运算优先级要高
            mid = low + ((high - low) >> 1);
            if (a[mid] >= target) {
                if (mid == 0 || a[mid - 1] < target) {
                    return mid;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    // 查找最后一个小于等于给定值的元素下标
    public static int findLastLessAndEqual(int[] a, int target) {
        int len = a.length;
        int low = 0, high = len - 1, mid;
        while (low <= high) {
            // 加法运算比位移运算优先级要高
            mid = low + ((high - low) >> 1);
            if (a[mid] <= target) {
                if (mid == len - 1 || a[mid + 1] > target) {
                    return mid;
                }
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // 如果有序数组是一个循环有序数组，比如 4，5，6，1，2，3。
    // 针对这种情况，如何实现一个求“值等于给定值”的二分查找算法呢？
    public static int findInRotatedArray(int[] nums, int target) {
        // 先找到原有序数组的起始元素的下标：即找到小于等于最后一个元素的第一个元素
        int len = nums.length;
        int low = 0, high = len - 1, mid = 0, last = nums[len - 1];
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (nums[mid] <= last) {
                if (mid == 0 || nums[mid - 1] > last) {
                    break;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        int offset = mid;
        if (nums[offset] == target) {
            return offset;
        }
        low = 0;
        high = len - 1;
        int readMid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            readMid = (mid + offset) % len;
            if (nums[readMid] < target) {
                low = mid + 1;
            } else if (nums[readMid] > target) {
                high = mid - 1;
            } else {
                return readMid;
            }
        }
        return -1;
    }
}
