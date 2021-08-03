package com.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    public static void main(String[] args) {
        int[] nums = {2,2,3};
        System.out.println(combinationSum(nums, 8));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(0, target, candidates, new ArrayList<>(), res);
        return res;
    }

    /**
     * 一颗n叉树
     * @param start 选择 [start, len - 1]部分的分支
     * @param target 剩余的要求的和
     */
    private static void dfs(int start, int target, int[] nums, List<Integer> temp, List<List<Integer>> res) {
        // 已经找到一个组合
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        // 选择 [start, len - 1]部分的分支
        for (int i = start; i < nums.length; i++) {
            // 剪枝 因为之前已经排过序了
            if (nums[i] > target) {
                break;
            }
            // 选择nums[i] 进入分支
            temp.add(nums[i]);
            // 继续选择[i, len - 1]的分支 因为不想要重复的组合但是可以选择相同的数多次 所以只选择>=i的数
            dfs(i, target - nums[i], nums, temp, res);
            temp.remove(temp.size() - 1);
        }
    }
}
