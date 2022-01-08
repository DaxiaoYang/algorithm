package com.algorithm.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/25
 */
public class FruitIntoBaskets {

    public static void main(String[] args) {
        int[] fruits = {3,3,3,1,2,1,1,2,3,3,4};
        System.out.println(totalFruit(fruits));
    }

    /**
     * 滑动窗口维持一个子数组 子数组中不同元素的个数 <= 2
     * 求子数组的最大长度
     */
    public static int totalFruit(int[] fruits) {
        Map<Integer, Integer> count = new HashMap<>();
        int res = 0;
        for (int i = 0, j = 0; j < fruits.length; j++) {
            count.put(fruits[j], count.getOrDefault(fruits[j], 0) + 1);
            while (count.size() > 2) {
                count.put(fruits[i], count.get(fruits[i]) - 1);
                if (count.get(fruits[i]) == 0) {
                    count.remove(fruits[i]);
                }
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
