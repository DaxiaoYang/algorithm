package com.algorithm.greedy;

import java.util.Arrays;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/25
 * 将求最少需要移除的区间个数 转化为 求最多可以保留非重叠区间个数
 */
public class NonOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        // 按右区间从小到大排序 右区间越小 给之后留下的空间就越大
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[1], i2[1]));
        int nonOverlappingNum = 1;
        int len = intervals.length;
        int[] last = intervals[0];
        for (int i = 1; i < len; i++) {
            // 当前区间与上一个不重叠区间 不重叠
            if (intervals[i][0] >= last[1]) {
                nonOverlappingNum++;
                last = intervals[i];
            }
        }
        return len - nonOverlappingNum;
    }
}
