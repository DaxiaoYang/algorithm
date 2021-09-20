package com.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/24
 */
public class findMinArrowShots {

    public static void main(String[] args) {
        int[][] points = new int[2][];
        points[0] = new int[]{-2147483646,-2147483645};
        points[1] = new int[]{2147483646,2147483647};
        System.out.println(findMinArrowShots(points));
    }

    public static int findMinArrowShots(int[][] points) {
        // 按左区间进行排序
        Arrays.sort(points, (p1, p2) -> Integer.compare(p1[0], p2[0]));
        int num = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > points[i - 1][1]) {
                // 区间不重合 则需要多一支箭
                num++;
            } else {
                // 区间重合 则复用该箭的条件是两个区间取右边最小的
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
            }
        }
        return num;
    }
}
