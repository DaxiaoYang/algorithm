package com.algorithm.backtracking;

public class Cal8Queen {

    // 数组下标对应的是行 数组中的值对应的是该行所选择的列的值 res[2] = 3 表示第三行选择列第四列（一维表示二维）
    private static int[] res = new int[8];

    public static void main(String[] args) {
        calRow(0);
    }

    public static void calRow(int row) {
        // 选择完毕
        if (row == 8) {
            print();
            return;
        }
        // 每一行都有八种选择
        for (int column = 0; column < 8; column++) {
            // 判断row行的column位置是否合理
            if (isValid(row, column)) {
                System.out.println("row: " + row + " col: " + column);
                // 若可行 则选择该位置
                res[row] = column;
                // 递归计算下一行
                calRow(row + 1);
            }
        }
    }

    private static boolean isValid(int row, int column) {
        // 要保证选择当前位置时 正上方 左对角线 右对角线都没有被选择：即值不为res[row] == column
        int leftUp = column - 1, rightUp = column + 1;
        for (int i = row - 1; i >= 0 ; i--) {
            if (res[i] == column) {
                return false;
            }
            if (leftUp >= 0 && res[i] == leftUp) {
                return false;
            }
            if (rightUp < 8 && res[i] == rightUp) {
                return false;
            }
            // 保证是两个对角线
            leftUp--;
            rightUp++;
        }
        return true;
    }

    public static void print() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (res[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
