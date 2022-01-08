package com.algorithmlesson.recursive;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/21
 */
public class Pow {

    /**
     * 二分法 每次都把n的规模减半 时间和空间复杂度都为logn
     * 1. 参数和返回值
     * @param x 底数
     * @param n 幂
     * @return x^n
     */
    public double myPow(double x, int n) {
        // 2. 终止条件 x n为 0 1的情况
        if (n == 0) {
            return 1;
        }
        if (x == 0 || x == 1 || n == 1) {
            return x;
        }
        // 负数转为正数进行计算 (x, n) = (1/x, -n) 但是n = Integer.MIN_VALUE时会发生上溢 -min = min
        // 所以n取负数之前先+1
        // (x, n) = x^n = 1/x * x^(n+1) = 1/x * (1/x, -(n + 1))
        if (n < 0) {
            return 1 / x * myPow(1 / x, -(n + 1));
        }
        // 3. 每次将n的规模减半 奇偶分开处理
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }
}
