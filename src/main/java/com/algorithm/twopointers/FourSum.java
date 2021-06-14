package com.algorithm.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/26
 */
public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 排序是为了去重和用双指针法
        Arrays.sort(nums);
        return kSum(nums, 0, 4, target);
    }

    private List<List<Integer>> kSum(int[] nums, int start, int k, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // base case 求two sum 有序数组中用双指针
        if (k == 2) {
            int left = start, right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    // 用链表是为了之后的头插法
                    List<Integer> temp = new LinkedList<>();
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    res.add(temp);
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }
            }
            return res;
        }
        for (int i = start; i < nums.length - k + 1; i++) {
            // 去重
            if (i > start && nums[i] == nums[i - 1]) continue;
            // reduce kSum to (k-1)Sum
            List<List<Integer>> subResults = kSum(nums, i + 1, k - 1, target - nums[i]);
            // 在头部补上当前元素
            for (List<Integer> subResult : subResults) {
                subResult.add(0, nums[i]);
            }
            res.addAll(subResults);
        }
        return res;
    }
}
