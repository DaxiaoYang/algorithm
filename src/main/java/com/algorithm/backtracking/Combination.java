package com.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combination {

    public static void main(String[] args) {
        System.out.println(combine(4, 2));
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfs(1,n,k,temp, res);
        return res;
    }

    /**
     * 表示选择 [start, n - k + 1]的分支
     * @param k 待选取的数
     */
    private static void dfs(int start, int n, int k, List<Integer> temp, List<List<Integer>> res) {
        // 没有待选取的数了
        if (k == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        // 选择 [start, n - k + 1]的分支 n - k + 1而非n 为剪枝
        // 因为剩余待选的数为k个 所以最多剪去那些不能得到k个数的分支
        // for循环选择的起始位置之后的元素个数 已经不足 我们需要的元素个数了，那么就没有必要搜索了
        for (int i = start; i <= n - k + 1; i++) {
            // 选择i ∈ [start, n - k + 1]
            temp.add(i);
            // 进入 i + 1
            dfs(i + 1, n, k - 1, temp, res);
            // 回退状态
            temp.remove(temp.size() - 1);
        }
    }

}
