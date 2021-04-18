package com.algorithm.backtracking;

import java.util.*;

public class WordBreak2 {

    public static void main(String[] args) {
        System.out.println(wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, new HashMap<>());
    }

    /**
     *
     * @param s 当前处理的子串
     * @param wordDict
     * @param memo
     * @return 返回s所有可能的分词集合
     */
    private static List<String> dfs(String s, List<String> wordDict, Map<String, List<String>> memo) {
        // 避免处理重复子串
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        List<String> res = new LinkedList<>();
        if (s.length() == 0) {
            // 说明遍历到字符串尾部了 必须加上空串 否则 for sub循环不会触发 最后一个word进不去
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                // 返回剩下待匹配的子串的 分词集合
                List<String> subList = dfs(s.substring(word.length()), wordDict, memo);
                // 与当前词组合
                for (String sub : subList) {
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }
        // 存储
        memo.put(s, res);
        return res;
    }
}
