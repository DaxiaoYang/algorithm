package com.algorithm.array;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/28
 */
public class UniqueOccurrences {

    public static void main(String[] args) {
        int[] arr = {1,2,2,1,1,3};
        System.out.println(uniqueOccurrences(arr));
    }

    public static boolean uniqueOccurrences(int[] arr) {
        // 用数组做哈希表 哈希函数就是 index = hash(x) = x
        int[] num2Frequency = new int[2001];
        // 输入的数字范围有限 但是会有负数 负数不能做数组下标 所以需要加一个偏移
        for (int num : arr) {
            num2Frequency[num + 1000]++;
        }
        // 再创建一个数组判断该频率是否重复出现
        boolean[] frequency = new boolean[1000];
        for (int freq : num2Frequency) {
            if (freq != 0) {
                if (frequency[freq]) {
                    // 表明重复出现了
                    return false;
                } else {
                    frequency[freq] = true;
                }
            }
        }
        return true;
    }
}
