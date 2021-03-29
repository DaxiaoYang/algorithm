package com.algorithm.dynamicprogramming;

import java.util.Arrays;

public class CoinSelect {

    private static int[] base = {0,1,1,2,2,1};

    public static void main(String[] args) {
        System.out.println(getMinCount(9));
        System.out.println(getMinCountWithMemo(9));
        System.out.println(getMinCountWithDP(9));
    }

    // backtrack
    public static int getMinCount(int n) {
        if (n <= 5) {
            return base[n];
        }
        return 1 + Math.min(getMinCount(n - 5), Math.min(getMinCount(n - 3), getMinCount(n - 1)));
    }

    // backtrack with memo
    public static int getMinCountWithMemo(int n) {
        int[] memo = Arrays.copyOf(base, n + 1);
        return getMinCountWithMemo(n, memo);
    }

    private static int getMinCountWithMemo(int n, int[] memo) {
        if (n <= 5) {
            return memo[n];
        }
        int res = 1 + Math.min(getMinCountWithMemo(n - 5, memo), Math.min(getMinCountWithMemo(n - 3, memo), getMinCountWithMemo(n - 1, memo)));
        memo[n] = res;
        return res;
    }

    // dp
    public static int getMinCountWithDP(int n) {
        int[] memo = Arrays.copyOf(base, n + 1);
        if (n <= 5) {
            return memo[n];
        }
        for (int i = 6; i <= n; i++) {
            memo[i] = 1 + Math.min(memo[i - 5], Math.min(memo[i - 3], memo[i - 1]));
        }
        return memo[n];
    }
}
