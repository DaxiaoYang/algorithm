package com.algorithmlesson.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/22
 */
public class Composition {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int start, int n, int k, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 剪枝优化 当可选择的元素 < 还需要选取的元素时 直接剪枝 想想n = 4 k = 4的情况
        for (int i = start; i <= n; i++) {
            if (n - i + 1 < k - path.size()) {
                break;
            }
            path.add(i);
            backtrack(i + 1, n, k, path, res);
            path.remove(path.size() - 1);
        }
    }
}
