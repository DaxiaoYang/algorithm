package com.algorithm.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/27
 */
public class PartitionLabels {

    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
    }

    public static List<Integer> partitionLabels(String s) {
        char[] chars = s.toCharArray();
        // 每个字母出现的最远距离
        int[] max = new int[26];
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            // 从左向右的 有的话就是最远的 不用加额外的判断条件
            max[chars[i] - 'a'] = i;
        }
        // 当前遍历到的字符的最右下标
        int currMax = 0;
        // 下一个区间的开始下标
        int lastIndex = 0;
        // 各区间长度
        List<Integer> sizes = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            // 更新当前遍历到的字符的最右下标 说明已经得了一个解
            currMax = Math.max(currMax, max[chars[i] - 'a']);
            // 如果遇到了 就马上划分区间
            if (i == currMax) {
                sizes.add(i - lastIndex + 1);
                lastIndex = i + 1;
            }
        }
        return sizes;
    }
}
