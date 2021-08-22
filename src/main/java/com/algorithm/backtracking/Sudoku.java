package com.algorithm.backtracking;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/10
 */
public class Sudoku {

    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    /**
     * 递归的每一层都比上一层多出一个数
     * @param board
     * @return
     */
    private boolean backtrack(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 已存在或者已选择
                if (board[i][j] != '.') {
                    continue;
                }
                // 对未选择的 尝试1-9
                for (char c = '1'; c <= '9'; c++) {
                    if (isValid(i, j, c, board)) {
                        board[i][j] = c;
                        // 有一组满足就返回
                        if (backtrack(board)) {
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                // 一个格子中尝试了1-9都不行 说明无解
                return false;
            }
        }
        // 填满了
        return true;
    }

    /**
     * 同行 同列 同九宫格不能有重复的
     * @param row
     * @param col
     * @param c
     * @param board
     * @return
     */
    private boolean isValid(int row, int col, char c, char[][] board) {
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == c) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c) {
                return false;
            }
        }
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == c) {
                    return false;
                }
            }
        }
        return true;
    }
}
