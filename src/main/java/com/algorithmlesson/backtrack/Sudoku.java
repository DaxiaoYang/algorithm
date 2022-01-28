package com.algorithmlesson.backtrack;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/21
 */
public class Sudoku {


    public static void main(String[] args) {
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        solveSudoku(board);
    }

    public static void solveSudoku(char[][] board) {
        backtrack(board, 0);
    }

    private static void backtrack(char[][] board, int i) {
        for (int j = 0; j < 9; j++) {
            if (board[i][j] == '.') {
                for (char num = '1'; num <= '9'; num++) {
                    if (isValid(board, i, j, num)) {
                        board[i][j] = num;
                        backtrack(board, i + 1);
                        board[i][j] = '.';
                    }
                }
            }
        }
    }

    private static boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num && i != row) {
                return false;
            }
        }
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == num && j != col) {
                return false;
            }
        }
        for (int i = row / 3; i < row / 3 + 3; i++) {
            for (int j = col / 3; j < col / 3 + 3; j++) {
                if (board[i][j] == num && (i != row && j != col)) {
                    return false;
                }
            }
        }
        return true;
    }
}
