package com.algorithm.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/19
 */
public class GasStation {

    public static void main(String[] args) {
        int[] gas = {5,1,2,3,4};
        int[] cost = {4,4,1,5,1};
        System.out.println(canCompleteCircuit(gas, cost));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalSum = 0;
        int currSum = 0;
        int startIndex = 0;
        for (int i = 0; i < gas.length; i++) {
            int dif =  gas[i] - cost[i];
            totalSum += dif;
            currSum += dif;
            // currSum < 0 说明[startIndex, i]这个区间内都不能作为起点
            if (currSum < 0) {
                // 选择下一个坐标
                startIndex = i + 1;
                // 重新计算currSum
                currSum = 0;
            }
        }
        // 总油数小于总耗油数 无解
        if (totalSum < 0) {
            return -1;
        }
        return startIndex;
    }
}
