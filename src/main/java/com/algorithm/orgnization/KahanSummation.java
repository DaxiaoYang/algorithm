package com.algorithm.orgnization;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/6
 */
public class KahanSummation {

    public static void main(String[] args) {
        float res = 0.0f;
        float remain = 0.0f;
        for (int i = 0; i < 20000000; i++) {
            float cur = 1.0f;
            float needToAdd = cur + remain;
            float nextRes = res + needToAdd;
            remain = needToAdd - (nextRes - res);
            if (remain > 0) {
                remain = remain;
            }
            res = nextRes;
        }
        System.out.println(res);
    }
}
