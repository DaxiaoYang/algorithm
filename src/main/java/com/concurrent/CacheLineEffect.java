package com.concurrent;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/7/4
 */
public class CacheLineEffect {

    private static final int LEN = 1024 * 1024;

    private static long[][] array = new long[LEN][];

    public static void main(String[] args) {
        for (int i = 0; i < LEN; i++) {
            array[i] = new long[8];
            for (int j = 0; j < 8; j++) {
                array[i][j] = 0L;
            }
        }
        long sum = 0L;
        long start = System.currentTimeMillis();
        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < 8; j++) {
                sum = array[i][j];
            }
        }
        System.out.println("times " + (System.currentTimeMillis() - start) + " ms");
        start = System.currentTimeMillis();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < LEN; i++) {
                sum = array[i][j];
            }
        }
        System.out.println("times " + (System.currentTimeMillis() - start) + " ms");
    }
}
