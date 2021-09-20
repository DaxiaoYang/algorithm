package com.algorithm.greedy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/30
 */
public class MonotoneIncreasingDigits {

    public static void main(String[] args) {
        System.out.println(monotoneIncreasingDigits(
                963856657));
    }

    public static int monotoneIncreasingDigits(int n) {
        List<Integer> digits = int2Array(n);
        // i >= flag的都为9
        int flag = digits.size();
        // 贪心：局部最优 98 -> 89 9减一 8->9  89<= 98且单调递增
        // 遍历顺序：从后向前 可以利用之前计算的结果 从前向后会覆盖结果
        for (int i = digits.size() - 1; i > 0; i--) {
            if (digits.get(i - 1) > digits.get(i)) {
                digits.set(i - 1, digits.get(i - 1) - 1);
                flag = i;
            }
        }
        for (int i = flag; i < digits.size(); i++) {
            digits.set(i, 9);
        }
        return array2int(digits);
    }

    private static int array2int(List<Integer> digits) {
        int num = 0;
        for (int digit : digits) {
            num *= 10;
            num += digit;
        }
        return num;
    }

    private static List<Integer> int2Array(int n) {
        List<Integer> temp = new LinkedList<>();
        while (n > 0) {
            temp.add(0, n % 10);
            n /= 10;
        }
        return temp;
    }
}
