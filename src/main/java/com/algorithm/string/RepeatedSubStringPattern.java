package com.algorithm.string;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/1
 */
public class RepeatedSubStringPattern {

    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        for (int subLen = len / 2; subLen >= 1; subLen--) {
            if (len % subLen == 0) {
                int times = len / subLen;
                String subString = s.substring(0, subLen);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < times; i++) {
                    sb.append(subString);
                }
                if (s.equals(sb.toString())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean repeatedSubstringPatternKMP(String s) {
        char[] c = s.toCharArray();
        int[] next = getNext(c);
        int len = c.length;
        // 如果该字符串有最长相同前后缀 len - next[len - 1] 为重复子串的第一个周期的长度 且周期长度能被整除
        // 则证明存在重复子串 且子串可构成该字符串
        if (next[len - 1] != 0 && len % (len - next[len - 1]) == 0) {
            return true;
        }
        return false;
    }

    private int[] getNext(char[] c) {
        int j = 0;
        int[] next = new int[c.length];
        for (int i = 1; i < c.length; i++) {
            while (j > 0 && c[j] != c[i]) {
                j = next[j - 1];
            }
            if (c[j] == c[i]) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
