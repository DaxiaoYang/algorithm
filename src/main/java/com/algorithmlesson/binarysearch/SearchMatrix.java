package com.algorithmlesson.binarysearch;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/28
 */
public class SearchMatrix {

    public static void main(String[] args) {
        int[][] matrix= new int[3][];
        matrix[0] = new int[]{1,3,5,7};
        matrix[1] = new int[]{10,11,16,20};
        matrix[2] = new int[]{23,30,34,60};
        System.out.println(searchMatrix(matrix, 3));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                i--;
            }
        }
        return false;
    }
}
