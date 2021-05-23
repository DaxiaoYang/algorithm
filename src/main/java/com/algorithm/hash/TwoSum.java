package com.algorithm.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/23
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new HashMap<>();
        int num, numToFind;
        for (int i = 0; i < nums.length; i++) {
            num = nums[i];
            numToFind = target - num;
            if (numToIndex.containsKey(numToFind)) {
                return new int[]{numToIndex.get(numToFind), i};
            }
            numToIndex.put(num, i);
        }
        return new int[]{-1, -1};
    }
}
