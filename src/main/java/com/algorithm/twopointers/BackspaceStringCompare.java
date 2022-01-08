package com.algorithm.twopointers;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/24
 */
public class BackspaceStringCompare {

    public static void main(String[] args) {
        System.out.println(backspaceCompare("ab##", "c#d#"));
    }

    public static boolean backspaceCompare(String s, String t) {
        // 从后往前遍历 就能确定一个字符是否需要被删除
        int i = s.length() - 1;
        int j = t.length() - 1;
        int numToDelete;
        while (true) {
            numToDelete = 0;
            // 找到s的下一个不被删除的字符
            while (i >= 0 && (numToDelete > 0 || s.charAt(i) == '#')) {
                numToDelete += s.charAt(i) == '#' ? 1 : -1;
                i--;
            }
            numToDelete = 0;
            // 找到t的下一个不被删除的字符
            while (j >= 0 && (numToDelete > 0 || t.charAt(j) == '#')) {
                numToDelete += t.charAt(j) == '#' ? 1 : -1;
                j--;
            }
            if (i >= 0 && j >= 0 && s.charAt(i) == t.charAt(j)) {
                i--;
                j--;
            } else {
                break;
            }
        }
        return i == -1 && j == -1;
    }
}
