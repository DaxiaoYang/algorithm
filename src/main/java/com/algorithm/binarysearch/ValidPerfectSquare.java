package com.algorithm.binarysearch;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/20
 */
public class ValidPerfectSquare {

    public static void main(String[] args) {
        System.out.println(isPerfectSquare(5));
    }

    public static boolean isPerfectSquare(int num) {
        int low = 1, high = num, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (mid < num / mid) {
                low = mid + 1;
            } else if (mid > num / mid) {
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean isPerfectSquare2(int num) {
        // 牛顿法 求出 f(x) = x^2 - num 的根 再判断是否是平方根
        long next = num;
        while (next > num / next) {
            next = (next + num / next) / 2;
        }
        return next * next == num;
    }
}
