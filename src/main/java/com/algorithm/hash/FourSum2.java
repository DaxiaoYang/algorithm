package com.algorithm.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/24
 */
public class FourSum2 {

    public static void main(String[] args) {
        int[] nums1 = {-1,-1};
        int[] nums2 = {-1,1};
        int[] nums3 = {-1,1};
        int[] nums4 = {1,-1};
        System.out.println(fourSumCount(nums1, nums2, nums3, nums4));
    }

    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        /*
         * 暴力解法是for * 4 判断 if (a + b + c + d == 0)
         * 这就等价于 (a + b) == -(c + d)
         */
        // 先计算出 a+b -> frequency
        Map<Integer, Integer> numToCount = new HashMap<>();
        for (int a : nums1) {
            for (int b : nums2) {
                numToCount.put(a + b, numToCount.getOrDefault(a + b, 0) + 1);
            }
        }
        int res = 0;
        for (int c : nums3) {
            for (int d : nums4) {
                // 如果符合条件 则加上出现次数 即组合数
                res += numToCount.getOrDefault(-(c + d), 0);
            }
        }
        return res;
    }

}
