package com.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 待选数字中会有重复的数 选出不重复的子集
 */
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
//        dfs(0, false, temp, nums, res);
        dfs2(0, temp, nums, res);
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


    /**
     * 有 [] nums[start] .. nums[len - 1]个分支可以选择 一个dfs代表一个决策阶段
     * @param start
     */
    private static void dfs2(int start, List<Integer> temp, int[] nums,
                     List<List<Integer>> res) {
        // 不选择任何元素 即选了[]
        res.add(new ArrayList<>(temp));
        // 选择nums[start] .. nums[len - 1]
        for (int i = start; i < nums.length; i++) {
            // 每个决策阶段重复的数只能选一次 因为出现重复的子集是因为有相邻的两个数 01 10（第一个选第二个不选）
            // 前面那个分支会有 10（也就是下面选了[]） 那么如果这个选了就会出现01了
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            dfs2(i + 1, temp, nums, res);
            temp.remove(temp.size() - 1);
        }
    }
}
