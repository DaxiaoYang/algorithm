package com.algorithm.backtracking;

public class SolvePackageByBackTracking2 {

    private static int[] weights = {1,2,3,2};

    private static int[] values = {2,5,7,6};

    private static int maxValue;

    private static int n = 4;
    private static int limit = 4;

    public static void main(String[] args) {
        dfs(0, 0, 0);
        System.out.println(maxValue);
    }

    public static void dfs(int i, int currrentWeight, int currentValue) {
        if (currrentWeight == limit || i == n) {
            maxValue = Math.max(maxValue, currentValue);
            return;
        }
        dfs(i + 1, currrentWeight, currentValue);
        if (currrentWeight + weights[i] <= limit) {
            dfs(i + 1, currrentWeight + weights[i], currentValue + values[i]);
        }
    }
}
