package com.algorithm.array;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/11/1
 */
public class SortArrayByParityII {

    public int[] sortArrayByParityII(int[] nums) {
        int[] res = new int[nums.length];
        int evenIndex = 0;
        int oddIndex = 1;
        for (int num : nums) {
            if (num % 2 == 0) {
                res[evenIndex] = num;
                evenIndex += 2;
            } else {
                res[oddIndex] = num;
                oddIndex += 2;
            }
        }
        return res;
    }

    public int[] sortArrayByParityII2(int[] nums) {
        // 原地算法
        for (int evenIndex = 0, oddIndex = 1; evenIndex < nums.length; evenIndex += 2) {
            // 找到偶位下的第一个奇数
            if (nums[evenIndex] % 2 == 1) {
                // 再找到奇位下的第一个偶数
                while (nums[oddIndex] % 2 == 1) {
                    oddIndex += 2;
                }
                // 交换
                swap(nums, evenIndex, oddIndex);
            }
        }
        return nums;
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
