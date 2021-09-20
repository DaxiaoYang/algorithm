package com.algorithm.greedy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/23
 */
public class ReconstructQueue {


    public int[][] reconstructQueue(int[][] people) {
        // 有两个维度的 先确定一个维度
        // 先按身高从大到小排序 再按比自己高的人头数从小到大排序
        Arrays.sort(people, (a, b) -> {
            // 返回的是自然序 小的在前面 大的在后面 a,b比较 返回负数就说明a比b小
            if (a[0] != b[0]) {
                return b[0] - a[0];
            }
            return a[1] - b[1];
        });
        List<int[]> list = new LinkedList<>();
        // 将元素插入对应下标位置即可 因为插入时 前面人的身高都比自己高
        for (int[] person : people) {
            list.add(person[1], person);
        }
        return list.toArray(new int[people.length][]);
    }
}
