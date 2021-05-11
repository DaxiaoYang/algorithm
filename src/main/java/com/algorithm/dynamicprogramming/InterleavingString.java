package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/11
 */
public class InterleavingString {

    public static void main(String[] args) {
        System.out.println(isInterleave("ab", "cd", "abcd"));
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray(), c3 = s3.toCharArray();
        int m = c1.length, n = c2.length;
        if (m + n != s3.length()) {
            return false;
        }
        // +1是因为i j 都可能出现==length()的情况 即字符用完了
        boolean[][] invalid = new boolean[m + 1][n + 1];
        return dfs(c1, c2, c3, 0, 0, 0, invalid);
    }

    /**
     * 表示s1[0 - i-1] s2[0 - j-1]的字符已经匹配完成 现在进行比较的分别是三个字符串中的i j k位置的字符
     * 当前比较的字符串为s1[i -> len] s2[j -> len] s3[i+j -> len]
     * @param invalid 缓存 不重复计算子问题   i + j == k 所以每个二维数组中的状态对应的k
     */
    private static boolean dfs(char[] c1, char[] c2, char[] c3, int i, int j, int k, boolean[][] invalid) {
        if (invalid[i][j]) {
            return false;
        }
        // s3中已经没有要比对的字符 表示比对完毕
        if (k == c3.length) {
            return true;
        }
        // 每个节点都有两种选择： 当前字符跟第一个字符串中的字符匹配或者跟第二个字符串中的字符匹配
        // 任意一个匹配成功且余下的字符也匹配成功则
        boolean valid = (i < c1.length && c1[i] == c3[k] && dfs(c1, c2, c3, i + 1, j, k + 1, invalid))
                || (j < c2.length && c2[j] == c3[k] && dfs(c1, c2, c3, i, j + 1, k + 1, invalid));
        if (!valid) {
            invalid[i][j] = true;
        }
        return valid;
    }
}
