package com.algorithm.string;

public class BM {

    public static void main(String[] args) {
        int[] suffix = new int[6];
        boolean[] prefix = new boolean[6];
        generateGS("cabcab".toCharArray(), 6, suffix, prefix);
    }

    // b表示模式串，m表示长度，suffix，prefix数组事先申请好了
    private static void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        for (int i = 0; i < m; ++i) {
             //初始化
            suffix[i] = -1; prefix[i] = false;
        }
        for (int i = 0; i < m - 1; ++i) {
            //b[0, i]
            int j = i; int k = 0;
            // 公共后缀子串长度
            while (j >= 0 && b[j] == b[m-1-k]) {
                // 与b[0, m-1]求公共后缀子串
                --j;
                ++k;
                suffix[k] = j+1;
                //j+1表示公共后缀子串在b[0, i]中的起始下标
            }
            if (j == -1) prefix[k] = true;
            //如果公共后缀子串也是模式串的前缀子串
        }
    }
}
