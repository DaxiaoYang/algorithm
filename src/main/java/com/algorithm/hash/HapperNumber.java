package com.algorithm.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/23
 */
public class HapperNumber {

    public boolean isHappy(int n) {
        // 快慢指针法
        int slow = n;
        int fast = n;
        do {
            slow = next(slow);
            fast = next(next(fast));
        } while (slow != fast);
        return slow == 1;
    }

    public boolean isHappyBySet(int n) {
        // 路径中已经访问过的数字
        Set<Integer> visited = new HashSet<>();
        while (visited.add(n)) {
            if ((n = next(n)) == 1) {
                return true;
            }
        }
        return false;
    }

    private int next(int num) {
        int sum = 0;
        int remainder = 0;
        while (num != 0) {
            remainder = num % 10;
            sum += remainder * remainder;
            num /= 10;
        }
        return sum;
    }
}
