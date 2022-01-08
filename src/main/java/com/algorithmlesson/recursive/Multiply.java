package com.algorithmlesson.recursive;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/22
 */
public class Multiply {

    public static void main(String[] args) {
        System.out.println(multiply(3, 10));
    }

    /**
     * 迭代
     */
    public static int multiplyIteratively(int A, int B) {
        int res = 0;
        for (int i = 0; i < 31; i++) {
            if (((1 << i) & B) != 0) {
                res += A << i;
            }
        }
        return res;
    }

    /**
     * 递归
     */
    public static int multiply(int A, int B) {
        return multiply(A, B, 0);
    }

    /**
     * 将乘法分解为多个值为1的位的位移
     * 1.参数和返回值
     * @param A 要相乘的两位数
     * @param B
     * @param bit 当前要考察的B的位数
     * @return B[bit, 30]的位数范围内与A的乘积 因为是正整数所以31位为0 不用考虑
     */
    private static int multiply(int A, int B, int bit) {
        // 2.终止条件
        if (bit == 31) {
            return 0;
        }
        // 3.单层递归逻辑 如果B的第bit位为1 则累加
        return (B & (1 << bit)) != 0 ? (A << bit) + multiply(A, B, bit + 1)
                : multiply(A, B, bit + 1);
    }
}
