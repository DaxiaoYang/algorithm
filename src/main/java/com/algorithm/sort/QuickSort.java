package com.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    private static Random random = new Random();
    public static void main(String[] args) {
        int[] a = {6,11,3,9,8};
        quickSort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void quickSort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    public static void quickSort(int[] a, int start, int end) {
        // 递归出口：数组长度为1
        if (start >= end) {
            return;
        }
        // 分区方法：将数组分为三个部分 前半部分的所有数均小于a[pivot] 后半部分的数均大于a[pivot]
        int pivot = partition(a, start, end);
        // 递归排序前半部分
        quickSort(a, start, pivot - 1);
        // 递归排序后半部分
        quickSort(a, pivot + 1, end);
    }

    public static int partition(int[] a, int start, int end) {
        // 随机化最后一个数
        swap(a, end, end - random.nextInt(end - start + 1));
        // 取最后一个数为pivot
        int pivot = a[end];
        // 使得[start, i-1]中的所有数为小于pivot的
        int i = start;
        for (int j = start; j < end; j++) {
            if (a[j] < pivot) {
                swap(a, i, j);
                i++;
            }
        }
        // 将最后一个数放到分割的位置上
        swap(a, i, end);
        // 返回其索引
        return i;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static int partition2(int[] a, int start, int end) {
        int pivot = a[end];
        int i = start, j = end - 1;
        while (i < j) {
            while (a[i] < pivot) i++;
            while (a[j] > pivot) j--;
            if (i > j) break;
            swap(a, i, j);
            i++;
            j--;
        }
        swap(a, i, end);
        return i;
    }
}
