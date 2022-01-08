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


    public int[][] generateMatrix2(int n) {
        // 循环次数
        int loopTimes = n / 2;
        int mid = n / 2;
        // 控制每条边的赋值范围
        int offset = 1;
        // 每次一圈循环的起点 [0,0] -> [1,1]
        int oneLoopStartX = 0;
        int oneLoopStartY = 0;
        int i;
        int j;
        int[][] res = new int[n][n];
        int count = 1;
        while (loopTimes > 0) {
            i = oneLoopStartX;
            j = oneLoopStartY;
            // 都是左闭右开区间 每条边都是只填充剩最后一位元素
            for (; j < oneLoopStartX + n - offset; j++) {
                res[i][j] = count++;
            }
            for (; i < oneLoopStartY + n - offset; i++) {
                res[i][j] = count++;
            }
            for (; j > oneLoopStartX; j--) {
                res[i][j] = count++;
            }
            for (; i > oneLoopStartY; i--) {
                res[i][j] = count++;
            }
            oneLoopStartX++;
            oneLoopStartY++;
            offset += 2;
            loopTimes--;
        }
        // 奇数需要特殊处理 如n = 3 则只有一圈循环 需要给最中间的格子赋值
        if (n % 2 == 1) {
            res[mid][mid] = count;
        }
        return res;
    }
}
