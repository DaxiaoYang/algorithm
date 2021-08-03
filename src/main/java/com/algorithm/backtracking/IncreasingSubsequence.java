package com.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/3
 */
public class IncreasingSubsequence {

    private static final int OFFSET = 100;

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, nums, res, new ArrayList<>());
        return res;
    }

    private void dfs(int start, int[] nums, List<List<Integer>> res, List<Integer> temp) {
        if (temp.size() >= 2) {
            res.add(new ArrayList<>(temp));
        }
        // 同一个父节点下不能有重复的 因为输入的数字范围有限 所以用数组代替哈希表
        int[] used = new int[201];
        for (int i = start; i < nums.length; i++) {
            if (used[nums[i] + OFFSET] == 1
                    || temp.size() > 0 && temp.get(temp.size() - 1) > nums[i]) {
                continue;
            }
            temp.add(nums[i]);
            used[nums[i] + OFFSET] = 1;
            dfs(i + 1, nums, res, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
