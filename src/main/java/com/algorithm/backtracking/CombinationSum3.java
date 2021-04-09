package com.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3 {

    public static void main(String[] args) {
        System.out.println(combinationSum3(3, 7));
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(1, n, k, res, new ArrayList<>());
        return res;
    }

    private static void dfs(int start, int n, int k, List<List<Integer>> res, List<Integer> temp) {
        // 已经选择了k个数了
        if (k == 0) {
            // 符合条件
            if (n == 0) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }
        // 所选择的数 1-9 但是i>n不会产生解
        int limit = n < 9 ? n : 9;
        for (int i = start; i <= limit; i++) {
            temp.add(i);
            dfs(i + 1, n - i, k - 1, res, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
