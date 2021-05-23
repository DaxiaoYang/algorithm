package com.algorithm.array;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/18
 */
public class SpiralMatrix2 {

    public static void main(String[] args) {
        System.out.println(generateMatrix(3));
    }

    public static int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        int num = 1;
        while (true) {
            // 从左到右
            for (int i = left; i <= right; i++) {
                res[top][i] = num++;
            }
            top++;
            if (!checkEdge(left, right, top, bottom)) {
                break;
            }
            // 从上到下
            for (int i = top; i <= bottom; i++) {
                res[i][right] = num++;
            }
            right--;
            // 从右到左
            for (int i = right; i >= left; i--) {
                res[bottom][i] = num++;
            }
            bottom--;
            if (!checkEdge(left, right, top, bottom)) {
                break;
            }
            // 从下到上
            for (int i = bottom; i >= top; i--) {
                res[i][left] = num++;
            }
            left++;
            if (!checkEdge(left, right, top, bottom)) {
                break;
            }
        }
        return res;
    }

    private static boolean checkEdge(int left, int right, int top, int bottom) {
        if (left > right || top > bottom) {
            return false;
        }
        return true;
    }
}
