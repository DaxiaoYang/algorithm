package com.algorithm.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] a = {7,8,3,9,4,5,2,1};
        mergeSort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void mergeSort(int[] a) {
        mergeSort(a, 0, a.length - 1);
    }

    public static void mergeSort(int[] a, int start, int end) {
        // 递归出口：数组长度为1时
        if (start >= end) {
            return;
        }
        // 取中点
        int mid = start + (end - start) / 2;
        // 递归排序左半部分
        mergeSort(a, start, mid);
        // 递归排序右半部分
        mergeSort(a, mid + 1, end);
        // 运行到这里时 [start, mid] [mid + 1, end]已经各自有序 将两个有序数组合并为一个
        merge(a, start, mid, end);
    }

    public static void merge(int[] a, int start, int mid, int end) {
        int i = start, j = mid + 1, k = 0;
        // 需要创建临时数组存储数组 因为同一时间内只会创建一个数组(只有位于线程的栈的顶部的方法才会被运行) 所以空间复杂度为O（n）
        int[] temp = new int[end - start + 1];
        while (i <= mid && j <= end) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        while (j <= end) {
            temp[k++] = a[j++];
        }
        k = 0; i = start;
        while (i <= end) {
            a[i++] = temp[k++];
        }
    }
}
