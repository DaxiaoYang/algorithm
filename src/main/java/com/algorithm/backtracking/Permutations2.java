package com.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations2 {

    public static void main(String[] args) {
        int[] nums = {1,2,1};
        System.out.println(permuteUnique(nums));
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        // 排序 让重复的元素靠在一起
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        dfs(new boolean[nums.length], nums, res, new ArrayList<>());
        return res;
    }

    private static void dfs(boolean[] chosen, int[] nums, List<List<Integer>> res, List<Integer> temp) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 如果当前决策的时候 已经选择了当前数字
            // 或者是存在重复的 且之前的没有选的时候（就说明两个重复的数字是在同一层决策中）
            // 跳过当前数字
            if (chosen[i] || (i > 0 && nums[i] == nums[i - 1] && !chosen[i - 1])) {
                continue;
            }
            chosen[i] = true;
            temp.add(nums[i]);
            dfs(chosen, nums, res, temp);
            temp.remove(temp.size() - 1);
            chosen[i] = false;
        }
    }
}
