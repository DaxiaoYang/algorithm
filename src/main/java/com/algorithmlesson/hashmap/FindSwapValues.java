package com.algorithmlesson.hashmap;

import java.util.*;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/2
 */
public class FindSwapValues {

    public static void main(String[] args) {
        int[] array1 = {4, 1, 2, 1, 1, 2};
        int[] array2 = {3, 6, 3, 3};
        System.out.println(findSwapValues(array1, array2));



    }

    /**
     * 公式推导
     * s1 - a + b = s2 - b + a
     * => s1 - s2 = 2(a - b) 所以是偶数
     * b = a - (s1 - s2) / 2
     * 所以只要存在数b为以上值 则可以交换
     * @param array1
     * @param array2
     * @return
     */
    public static int[] findSwapValues(int[] array1, int[] array2) {
        int sum1 = 0;
        int sum2 = 0;
        Set<Integer> set2 = new HashSet<>();
        for (int num : array1) {
            sum1 += num;
        }
        for (int num : array2) {
            sum2 += num;
            set2.add(num);
        }
        int diff = sum1 - sum2;
        if (diff % 2 != 0) {
            return new int[]{};
        }
        int other;
        for (int num : array1) {
            other = num - diff / 2;
            if (set2.contains(other)) {
                return new int[]{num, other};
            }
        }
        return new int[]{};
    }
}
