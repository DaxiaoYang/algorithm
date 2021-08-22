package com.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/9
 */
public class NQueues {

    public List<List<String>> solveNQueens(int n) {
        char[][] matrix = new char[n][];
        for (int i = 0; i < n; i++) {
            matrix[i] = new char[n];
            Arrays.fill(matrix[i], '.');
        }
        List<List<String>> res = new ArrayList<>(n);
        backtrack(0, n, res, matrix);
        return res;
    }

    private void backtrack(int row, int n, List<List<String>> res, char[][] matrix) {
        if (row == n) {
            res.add(getResult(matrix));
            return;
        }
        for (int j = 0; j < matrix[row].length; j++) {
            if (isValid(row, j, matrix)) {
                matrix[row][j] = 'Q';
                backtrack(row + 1, n, res, matrix);
                matrix[row][j] = '.';
            }
        }
    }

    /**
     * todo 看看优化 https://leetcode.com/problems/n-queens/discuss/19805/My-easy-understanding-Java-Solution
     * @param row
     * @param col
     * @param matrix
     * @return
     */
    private boolean isValid(int row, int col, char[][] matrix) {
        for (int i = row - 1; i >= 0; i--) {
            if (matrix[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (matrix[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < matrix[0].length; i--, j++) {
            if (matrix[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private List<String> getResult(char[][] matrix) {
        List<String> ret = new ArrayList<>(matrix.length);
        StringBuilder sb = new StringBuilder(matrix.length);
        for (char[] row : matrix) {
            for (char c : row) {
                sb.append(c);
            }
            ret.add(sb.toString());
            sb.setLength(0);
        }
        return ret;
    }
}
