package com.algorithmlesson.programme;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/11/30
 */
public class Atoi {

    public static void main(String[] args) {
        System.out.println(myAtoi("9223372036854775808"));
    }

    public static int myAtoi(String s) {
        if (s.length() == 0) {
            return 0;
        }
        char[] c = s.toCharArray();
        int len = c.length;
        int i = 0;
        while (i < len && c[i] == ' ') {
            i++;
        }
        if (i == len) {
            return 0;
        }
        long sign = 1;
        if (c[i] == '-') {
            sign = -1;
            i++;
        } else if (c[i] == '+') {
            i++;
        }
        if (i == len || !Character.isDigit(c[i])) {
            return 0;
        }
        while (i < len && c[i] == '0') {
            i++;
        }
        long res = 0;
        int digitLen = 0;
        while (i < len && Character.isDigit(c[i])) {
            res = res * 10 + (c[i] - '0');
            i++;
            digitLen++;
        }
        if (digitLen >= 19) {
            if (sign == 1) {
                res = Integer.MAX_VALUE;
            } else {
                res = Integer.MIN_VALUE;
            }
        } else {
            res = sign * res;
        }
        if (res < Integer.MIN_VALUE) {
            res = Integer.MIN_VALUE;
        }
        if (res > Integer.MAX_VALUE) {
            res = Integer.MAX_VALUE;
        }
        return (int)res;
    }
}
