package com.algorithm.string;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/11/20
 */
public class BackspaceStringCompare {

    public static void main(String[] args) {
        backspaceCompare("a#c", "b");
    }

    public static boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int sSkipCount = 0;
        int tSkipCount = 0;
        while (true) {
            // 从后向前 模拟删除元素的过程
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    sSkipCount++;
                } else {
                    if (sSkipCount > 0) {
                        sSkipCount--;
                    } else {
                        break;
                    }
                }
                i--;
            }
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    tSkipCount++;
                } else {
                    if (tSkipCount > 0) {
                        tSkipCount--;
                    } else {
                        break;
                    }
                }
                j--;
            }
            // i j 都来到了删除元素后的位置 比较字符
            if (s.charAt(i) != t.charAt(j)) {
                return false;
            }
            if (i == -1 || j == -1) {
                break;
            }
            i--;
            j--;
        }
        return i == j;
    }
}
