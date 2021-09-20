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

    /**
     * 完全背包问题 物品是 1 4 9 16..这些完全平方数
     * @param n 背包容量
     */
    public int numSquares2(int n) {
        // 1.dp[j] 和为j 所需的最少的完全平方数
        int[] dp = new int[n + 1];
        // 3.每个数所需的最多的完全平方数就是 i 个 1 相加
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
        }
        // 4. 先遍历物品 再遍历背包容量(正序 因为可以选择多个物品)
        // square用于存储中间结果避免重复计算
        // 因为n <= 10^4 所以n * n <= 10^8 < Integer.MAX_VALUE 可以不考虑整数上溢的情况
        int square;
        for (int i = 2; (square = i * i) <= n; i++) {
            for (int j = square; j <= n; j++) {
                // 2. dp[j] = min(dp[j], dp[j - i * i] + 1) 对每个物品 选或者不选
                dp[j] = Math.min(dp[j], dp[j - square] + 1);
            }
        }
        return dp[n];
    }
}
