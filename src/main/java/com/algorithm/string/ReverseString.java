package com.algorithm.string;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/27
 */
public class ReverseString {

    public void reverseString(char[] s) {
        for (int i = 0, j = s.length - 1; i < j; i++, j--) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }
}
