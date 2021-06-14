package com.algorithm.string;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/27
 */
public class ReverseString2 {

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        // 对于每个2k字符 逆转前k个字符
        for (int i = 0; i < chars.length; i += 2 * k) {
            reverse(chars, i, i + k);
        }
        return String.valueOf(chars);
    }

    private void reverse(char[] chars, int start, int end) {
        end = Math.min(chars.length, end) - 1;
        for (; start < end; start++, end--) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
        }
    }
}
