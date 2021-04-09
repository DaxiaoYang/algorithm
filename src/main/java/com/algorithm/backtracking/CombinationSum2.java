package com.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 选择不重复的组合 且每个数字只能用一次
 */
public class CombinationSum2 {

    public static void main(String[] args) {
        int[] nums = {2,1,2,};
        System.out.println(combinationSum2(nums, 3));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 先排序 将数字相等的字符放到相邻的位置
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, target, candidates, res, new ArrayList<>());
        return res;
    }

    /**
     * 当前有n个（len - start + 1个）分支可以进行选择
     * @param start
     * @param target 当前所需凑够的数
     */
    private static void dfs(int start, int target, int[] candidates, List<List<Integer>> res, List<Integer> temp) {
        // 发现一个可行解
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 每个阶段的第一个是必须要选的 因为不会跟其他的选择重复 但是后面的重复的不能选
            if (i > start && candidates[i] == candidates[i - 1] || candidates[i] > target) {
                continue;
            }
            temp.add(candidates[i]);
            // 不能重复选择数 所以i + 1
            dfs(i + 1, target - candidates[i], candidates, res, temp);
            temp.remove(temp.size() - 1);
        }
    }
}