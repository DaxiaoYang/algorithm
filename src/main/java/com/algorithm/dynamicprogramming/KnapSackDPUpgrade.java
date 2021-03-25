package com.algorithm.dynamicprogramming;

public class KnapSackDPUpgrade {
    private static int[] weights = {1,2,3,2};

    private static int[] values = {2,5,7,6};

    private static int maxValue;

    private static int n = 4;
    private static int limit = 4;
    
    

    public static void main(String[] args) {
//        System.out.println(getMaxValue());
        for (int i = 0; i <= 200; i++) {
            if (i % 7 == 0 && i % 4 != 0) {
                System.out.print(i + " ");
            }
        }
    }

    public static int getMaxValue() {
        int[][] states = new int[n][limit + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= limit; j++) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (weights[0] <= limit) {
            states[0][weights[0]] = values[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= limit; j++) {
                if (states[i - 1][j] != -1) {
                    states[i][j] = states[i - 1][j];
                }
            }
            for (int j = 0; j <= limit - weights[i]; j++) {
                if (states[i - 1][j] != -1) {
                    states[i][j + weights[i]] = Math.max(states[i][j + weights[i]], states[i - 1][j] + values[i]);
                }
            }
        }
        for (int j = limit; j >= 0; j--) {
            maxValue = Math.max(maxValue, states[n - 1][j]);
        }
        return maxValue;
    }
}
