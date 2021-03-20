package com.algorithm.search;

import java.math.BigDecimal;

public class FindSqrt {

    public static void main(String[] args) {
        System.out.println(findSqrt(9));
    }

    public static double findSqrt(double n) {
        double curr = n, next, eps = 1e-6;
        while (true) {
            next = (curr + n / curr) / 2;
            if (Math.abs(curr - next) < eps) {
                break;
            }
            curr = next;
        }
        return new BigDecimal(curr).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
