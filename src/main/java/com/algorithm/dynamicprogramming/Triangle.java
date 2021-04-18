package com.algorithm.dynamicprogramming;

import java.util.List;

public class Triangle {

    /**
     *
     * (i,j) = min((i-1,j-1), (i-1,j)) + (i,j) (j!=0 j != i)
     * (i,j) = (i-1,j) + (i,j) (i == 0)
     * (i,j) = (i-1,j-1) + (i,j) (i == j)
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        for (int i = 1; i < n; i++) {
            List<Integer> prev = triangle.get(i - 1);
            List<Integer> curr = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                int prevSum;
                if (j == 0) {
                    prevSum = prev.get(j);
                } else if (j == i) {
                    prevSum = prev.get(j - 1);
                } else {
                    prevSum = Math.min(prev.get(j), prev.get(j - 1));
                }
                curr.set(j, curr.get(j) + prevSum);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int res : triangle.get(n - 1)) {
            min = Math.min(res, min);
        }
        return min;
    }
}
