package com.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 求全排列
 */
public class Permutations {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(permute(nums));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // boolean在数组情况下是1个字节 单独一个boolean是4个字节
        dfs(new boolean[nums.length], nums, res, new ArrayList<>());
        return res;
    }

    /**
     *
     * @param chosen 表示当前决策时 nums[i]中的数字有没有被选中
     */
    private static void dfs(boolean[] chosen, int[] nums, List<List<Integer>> res, List<Integer> temp) {
        // 已经凑够了个数
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        // 每一次的可以选择的分支是 之前没有选择过的数字 同一个路径下没有被选择的远古三
        for (int i = 0; i < nums.length; i++) {
            if (chosen[i]) {
                continue;
            }
            temp.add(nums[i]);
            chosen[i] = true;
            dfs(chosen, nums, res, temp);
            temp.remove(temp.size() - 1);
            chosen[i] = false;
        }
    }
}
