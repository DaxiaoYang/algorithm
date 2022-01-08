package com.algorithm.string;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/11/19
 */
public class LongPressedName {

    public static void main(String[] args) {
        System.out.println(isLongPressedName("alex", "aaleexa"));
    }

    public static boolean isLongPressedName(String name, String typed) {
        int m = name.length();
        int n = typed.length();
        int i = 0;
        for (int j = 0; j < n; j++) {
            if (i < m && name.charAt(i) == typed.charAt(j)) {
                i++;
            } else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1)) {
                // 如果是第一个就不等 或者不等时不是后面出现的重复元素
                return false;
            }
        }
        return i == m;
    }
}
