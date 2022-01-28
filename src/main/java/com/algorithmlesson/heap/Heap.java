package com.algorithmlesson.heap;

import java.util.Arrays;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/18
 */
public class Heap {

    private int[] values;

    private int capacity;

    private int count;

    public Heap(int capacity) {
        this.capacity = capacity;
        values = new int[capacity + 1];
    }

    public static void buildHeap(int[] nums, int n) {
        for (int i = n / 2; i >= 1; i--) {
            heapify(i, nums, n);
        }
    }

    public static void heapSort(int[] nums, int n) {
        // 假设nums中的数据的存储是从[1,n]
        buildHeap(nums, n);
        int len = n;
        while (len > 1) {
            swap(nums, 1, len);
            len--;
            heapify(1, nums, len);
        }
    }

    public void add(int value) {
        values[++count] = value;
        int i = count;
        while (i / 2 > 0) {
            if (values[i / 2] < values[i]) {
                swap(values, i / 2, i);
            }
            i = i / 2;
        }
    }

    public int pop() {
        int res = values[1];
        swap(values, 1, count);
        count--;
        heapify(1, values, count);
        return res;
    }



    private static void heapify(int i, int[] values, int count) {
        int maxPos;
        while (true) {
            maxPos = i;
            if (2 * i <= count && values[2 * i] > values[i]) {
                maxPos = 2 * i;
            }
            if (2 * i + 1 <= count && values[2 * i + 1] > values[maxPos]) {
                maxPos = 2 * i + 1;
            }
            if (maxPos == i) {
                break;
            }
            swap(values, i, maxPos);
            i = maxPos;
        }
    }

    public int top() {
        if (count == 0) {
            return -1;
        }
        return values[1];
    }

    private static void swap(int[] values, int i, int j) {
        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }

    public static void main(String[] args) {
//        Heap maxHeap = new Heap(10);
//        maxHeap.add(1);
//        maxHeap.add(2);
//        maxHeap.add(4);
//        maxHeap.add(3);
//        System.out.println(maxHeap.pop());
//        System.out.println(maxHeap.pop());
//        System.out.println(maxHeap.pop());
//        System.out.println(maxHeap.pop());
        int[] nums = {0, 3, 2, 1, 5};
        heapSort(nums, 4);
        System.out.println(Arrays.toString(nums));
    }
}
