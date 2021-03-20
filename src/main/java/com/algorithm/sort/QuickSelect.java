package com.algorithm.sort;

import static com.algorithm.sort.QuickSort.swap;

public class QuickSelect {
    public static void main(String[] args) {
        int[] a = {4,2,5,12,3};
        System.out.println(findKthLargest(a, 1));
    }

    public static int findKthLargest(int[] a, int k) {
        return quickSelect(a, 0, a.length - 1, a.length - k);
    }

    public static int quickSelect(int[] a, int start, int end, int k) {
        if (start >= end) {
            return start;
        }
        int pivot = partition(a, start, end);
        if (pivot == k) {
            return a[pivot];
        } else if (pivot > k) {
            return quickSelect(a, start, pivot - 1, k);
        } else {
            return quickSelect(a, pivot + 1, end, k);
        }
    }

    public static int partition(int[] a, int start, int end) {
        int pivot = a[end];
        int i = start;
        for (int j = start; j < end; j++) {
            if (a[j] < pivot) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, i, end);
        return i;
    }
}
