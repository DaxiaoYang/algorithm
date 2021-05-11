package com.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PerfectSquares {

    public static void main(String[] args) {
        System.out.println(numSquares(12));
        List<String> wordDict = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        Collections.sort(wordDict, (s1, s2) -> s2.length() - s1.length());
        System.out.println(wordDict);
    }

    public static int numSquaresDp2(int n) {
        // status[i]表示和为i的最少需要的完美平方数
        int[] status = new int[n + 1];
        Arrays.fill(status, Integer.MAX_VALUE);
        // 边界条件
        status[0] = 0;
        // 当前位置的状态由前面位置的状态决定 取其中的最小值
        // status(i) = min(1 + status(i - j * j))  j * j <= i, j >= 1
        // 先将需要的平方数计算好 避免之后重复计算
        int[] squares = new int[(int)Math.sqrt(n) + 1];
        for (int i = 1; i < squares.length; i++) {
            squares[i] = i * i;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < squares.length && squares[j] <= i; j++) {
                status[i] = Math.min(status[i], 1 + status[i - squares[j]]);
            }
        }
        return status[n];
    }

    public static int numSquaresDp(int n) {
        // status[i]表示和为i的最少需要的完美平方数
        int[] status = new int[n + 1];
        Arrays.fill(status, Integer.MAX_VALUE);
        // 边界条件
        status[0] = 0;
        // 当前位置的状态由前面位置的状态决定 取其中的最小值
        // status(i) = min(1 + status(i - j * j))  j * j <= i, j >= 1
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                status[i] = Math.min(status[i], 1 + status[i - j * j]);
            }
        }
        return status[n];
    }

    public static int numSquares(int n) {
        List<Integer> square = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            square.add(i * i);
        }
        int[] memo = new int[n + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        return dfs(n, square, memo);
    }

    private static int dfs(int target, List<Integer> square, int[] memo) {
        if (target < 4) {
            return target;
        }
        if (memo[target] != Integer.MAX_VALUE) {
            return memo[target];
        }
        for (int i = 0; i < square.size() && square.get(i) <= target; i++) {
            int res = 1 + dfs(target - square.get(i), square, memo);
            memo[target] = Math.min(res, memo[target]);
        }
        return memo[target];
    }
}
