package com.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/7/23
 */
public class PalindromePartition {


    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(0, s, res, new ArrayList<>());
        return res;
    }

    /**
     * 切割字符串问题可以看成一个组合问题 每次选取多少个字母
     * @param start 切割线
     */
    private void dfs(int start, String s, List<List<String>> res, List<String> path) {
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (!isPalindrome(s, start, i + 1)) {
                continue;
            }
            path.add(s.substring(start, i + 1));
            dfs(i + 1, s, res, path);
            path.remove(path.size() - 1);
        }
    }

    private boolean isPalindrome(String s, int low, int high) {
        for (int i = low, j = high - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
