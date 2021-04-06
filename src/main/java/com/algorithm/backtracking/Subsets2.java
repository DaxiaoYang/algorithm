package com.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets2 {

    public static void main(String[] args) {
        int[] nums = {2,1,2};
        System.out.println(subsetsWithDup(nums));
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        // 排序 将值相同的数 靠在一起
        Arrays.sort(nums);
        dfs(0, false, temp, nums, res);
        return res;
    }

    /**
     *
     * @param i 当前决策位置
     * @param choosePrev 是否选择了上一个数
     */
    private static void dfs(int i, boolean choosePrev, List<Integer> temp, int[] nums,
                     List<List<Integer>> res) {
        // 到达叶子节点
        if (i == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        // 不选择当前元素
        dfs(i + 1, false, temp, nums, res);
        // 避免出现重复的子集：有两个相同的数（只需要考虑相邻的两个数即可）
        // 挨在一起 01 10 这两种情况导致的子集是一样的 （一个选一个不选）
        if (i > 0 && nums[i] == nums[i - 1] && !choosePrev) {
            return;
        }
        // 选择当前的元素
        temp.add(nums[i]);
        dfs(i + 1, true, temp, nums, res);
        // 还原temp内容 因为要回归到父节点了
        temp.remove(temp.size() - 1);
    }
}
