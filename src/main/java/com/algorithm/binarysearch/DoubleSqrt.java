package com.algorithm.binarysearch;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/11/11
 */
public class DoubleSqrt {

    private static final double DISTANCE = 0.000001;

    public static void main(String[] args) {
        System.out.println(sqrt(5));
    }

    public static double sqrt(float num) {
        float low = 0, high = num;
        float mid, product;
        while (Math.abs(high - low) >= DISTANCE) {
            mid = (low + high) / 2;
            product = mid * mid;
            if (num - product > DISTANCE) {
                low = mid;
            } else if (product - num > DISTANCE) {
                high = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
