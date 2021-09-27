package com.algorithm.dynamicprogramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {


    public boolean wordBreakDp(String s, List<String> wordDict) {
        // List 转 Hashset提高查询效率
        Set<String> set = new HashSet<>(wordDict);
        int len = s.length();
        // status[i]=true 表示 [0, i - 1]可以由字典里面的数字构成
        boolean[] status = new boolean[len + 1];
        // 边界
        status[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                // 如果[0, j - 1]可以由字典构成 且 [j, i - 1]可以由字典构造
                if (status[j] && set.contains(s.substring(j, i))) {
                    // [0, i - 1]可以由字典构成
                    status[i] = true;
                    break;
                }
            }
        }
        return status[len];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        return dfs(s, set);
    }

    private boolean dfs(String s, Set<String> wordDict) {
        int len = s.length();
        if (len == 0) {
            return true;
        }
        for (int i = 1; i <= len; i++) {
            if (wordDict.contains(s.substring(0, i)) && dfs(s.substring(i), wordDict)) {
                return true;
            }
        }
        return false;
    }


    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        // 1.dp[i] = true 表示长度为i的字符串可由集合中的字符串表示
        boolean[] dp = new boolean[s.length() + 1];
        // 3. 初始化 无意义
        dp[0] = true;
        // 4. 先遍历背包容量 方便求substring 内层正序 因为物品可以选多次（完全背包）
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // 2. 当dp[j] = true 且 [j, i]这个子串在集合中时  dp[i]为true
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
