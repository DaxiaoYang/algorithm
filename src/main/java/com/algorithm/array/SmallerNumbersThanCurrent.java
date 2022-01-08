package com.algorithm.array;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/27
 */
public class SmallerNumbersThanCurrent {

    public int[] smallerNumbersThanCurrent(int[] nums) {
        // 计数排序的思想
        int[] times = new int[101];
        for (int num : nums) {
            times[num]++;
        }
        int count = 0;
        // 进行处理 使得times[i] 中的值 为小于i的个数
        for (int i = 0; i < times.length; i++) {
            if (times[i] != 0) {
                int temp = times[i];
                times[i] = count;
                count += temp;
            }
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = times[nums[i]];
        }
        return res;
    }
}
