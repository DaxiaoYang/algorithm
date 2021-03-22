package com.algorithm.backtracking;

public class Pattern {

    private static boolean matched;

    public static void main(String[] args) {
        char[] text = {'0'};
        char[] pattern = {'*', '0', '?'};
        System.out.println(match(text, pattern));
    }


    public static boolean match(char[] text, char[] pattern) {
        matched = false;
        match(0, 0, text, pattern);
        return matched;
    }

    /**
     * 正则匹配 *对应0个或者多个字符 ？对应0个或者一个字符
     * @param i 文本串下标
     * @param j 正则字符串下标
     * @param text
     * @param pattern
     */
    private static void match(int i, int j, char[] text, char[] pattern) {
        // 如果已经匹配 就不用继续递归了 剪枝
        if (matched) {
            return;
        }
        // 当两个指针都走到末尾 说明匹配完毕
        if (i == text.length && j == pattern.length) {
            matched = true;
            return;
        }
        if (pattern[j] == '*') {
            // 有text.length - i + 1（text中待匹配的字符数量）个分支可以走 即*匹配0，1.. text.length-1个字符
            for (int k = 0; k <= text.length - i; k++) {
                match(i + k, j + 1, text, pattern);
            }
        } else if (pattern[j] == '?') {
            // ？ 匹配0个字符的分支
            match(i, j + 1, text, pattern);
            // ？ 匹配1个字符的分支
            match(i + 1, j + 1, text, pattern);
        } else if (i < text.length && j < pattern.length && text[i] == pattern[j]) {
            match(i + 1, j + 1, text, pattern);
        }
    }
}
