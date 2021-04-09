package com.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(permute(nums));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, nums.length, new boolean[nums.length], nums, res, new ArrayList<>());
        return res;
    }

    private static void dfs(int start, int len, boolean[] choosen, int[] nums, List<List<Integer>> res, List<Integer> temp) {
        if (len == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i == start && len != nums.length || choosen[i]) {
                continue;
            }
            temp.add(nums[i]);
            choosen[i] = true;
            dfs(i, len - 1, choosen, nums, res, temp);
            temp.remove(temp.size() - 1);
            choosen[i] = false;
        }
    }
}
