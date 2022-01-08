package com.algorithm.array;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/28
 */
public class ValidMountainArray {


    public boolean validMountainArray(int[] arr) {
        int len = arr.length;
        // 双指针 模拟两个人爬山 如果爬的是同一座山 最终会到达相同的顶点
        int left = 0, right = len - 1;
        while (left + 1 < len && arr[left + 1] > arr[left]) {
            left++;
        }
        while (right > 0 && arr[right - 1] > arr[right]) {
            right--;
        }
        // 在起始位置说明是一个单调递增或递减的山峰
        return left > 0 && right < len - 1 && left == right;
    }
}
