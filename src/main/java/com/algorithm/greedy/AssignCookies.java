package com.algorithm.greedy;

import java.util.Arrays;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/11
 * 局部最优解 ——> 全局最优解
 */
public class AssignCookies {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int index = 0;
        // 小饼干先喂饱小胃口的孩子
        for (int i = 0; i < s.length && index < g.length; i++) {
            // 若当前大小的饼干能喂饱这个孩子
            if (g[index] <= s[i]) {
                count++;
                index++;
            }
        }
        return count;
    }
}
