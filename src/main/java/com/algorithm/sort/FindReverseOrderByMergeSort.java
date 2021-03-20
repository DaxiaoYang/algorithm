package com.algorithm.sort;

public class FindReverseOrderByMergeSort {

    public static int sum;

    public static void main(String[] args) {
//        int[] a = {3,2,4,5,6,1};
        int[] a = {6,5,4,3,2,1};
        mergeSort(a);
        System.out.println(sum);
    }

    public static void mergeSort(int[] a) {
        mergeSort(a, 0, a.length - 1);
    }

    public static void mergeSort(int[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + ((high - low) >> 1);
        mergeSort(a, low, mid);
        mergeSort(a, mid + 1, high);
        merge(a, low, mid, high);
    }

    public static void merge(int[] a, int low, int mid, int high) {
        int i = low, j = mid + 1, k = 0;
        int[] temp = new int[high - low + 1];
        while (i <= mid && j <= high) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                sum += mid - i + 1;
                temp[k++] = a[j++];
            }
        }
        i = (i < mid) ? i : j;
        while (k < temp.length) {
            temp[k++] = a[i++];
        }
        for (k = 0; k < temp.length; k++) {
            a[low + k] = temp[k];
        }
    }
}
