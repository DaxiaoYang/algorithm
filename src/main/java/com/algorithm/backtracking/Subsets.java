package com.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(subsets(nums));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfs(0, temp, nums, results);
        return results;
    }

    /**
     *
     * @param i 表示当前决策是否将nums[i]放入结果集中 因为没有重复的子问题 所以回溯算法就是最优的 二叉树
     *  时间复杂度为 O(2^n)
     */
    private static void dfs(int i, List<Integer> temp, int[] nums, List<List<Integer>> results) {
        // 到达叶子节点 将一种可能添加进去
        if (i == nums.length) {
            results.add(new ArrayList<>(temp));
            return;
        }
        // 不选择当前元素
        dfs(i + 1, temp, nums, results);
        // 选择当前元素
        temp.add(nums[i]);
        dfs(i + 1, temp, nums, results);
        // 恢复temp中的内容
        temp.remove(temp.size() - 1);
    }

    /**
     * 组合和分割问题都是找递归树的叶子结点 子集问题是求树的所有结点
     * n叉树 每次决策都有 len - start + 2个分支可以走 即不选择任何一个数 或者选择nums[start..len - 1]
     * @param start
     */
    private static void dfs2(int start, List<Integer> temp, int[] nums, List<List<Integer>> results) {
        // 不选择任何一个分支（数）
        results.add(new ArrayList<>(temp));
        // 选择nums[start]..nums[len - 1]这几个分支（数）
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            dfs2(i + 1, temp, nums, results);
            temp.remove(temp.size() - 1);
        }
    }
}
