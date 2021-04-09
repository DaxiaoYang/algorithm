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
        // 选择 [start, n - k + 1]的分支
        for (int i = start; i <= n - k + 1; i++) {
            temp.add(i);
            // 选择 [i + 1, n - k + 2]的分支
            dfs(i + 1, n, k - 1, temp, res);
            temp.remove(temp.size() - 1);
        }
    }
}
