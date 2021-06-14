package com.data_structure;

import java.util.Arrays;

public class Heap {

    private int[] value;
    private int capacity;
    private int size;

    public Heap(int capacity) {
        this.capacity = capacity;
        value = new int[capacity + 1];
    }

    public static void main(String[] args) {
        Heap heap = new Heap(6);
//        for (int i = 1; i <= 6; i++) {
//            heap.insert(i);
//        }
//        for (int i = 0; i < 3; i++) {
//            System.out.println(heap.removeMax());
//        }

        int[] a = {0,4,2,1,5,7,6};
        heap.sort(a, 6);
        System.out.println(Arrays.toString(a));
    }

    public void sort(int[] a, int n) {
        // 建堆 O（n）
        buildHeap(a, n);
        int k = n;
        // k是要与1进行交换的元素位置
        while (k > 1) {
            // 破坏根特性 且将最大值放到了末尾
            swap(a, 1, k--);
            // 对堆中剩下的元素 从根节点开始自上而下堆化
            heapify(a, k, 1);
        }
    }

    private void buildHeap(int[] a, int n) {
        // 对所有非叶子节点进行堆化
        for (int i = n / 2; i >= 1; i--) {
            heapify(a, n, i);
        }
    }

    public void insert(int n) {
        if (size == capacity) {
            return;
        }
        // 数组从下标为1开始存储 便于计算 但是会浪费一个存储空间
        value[++size] = n;
        int i = size;
        // （自下而上构造大根堆）当存在父节点 且父节点的值小于当前节点 -> 交换
        while ((i / 2 > 0) && (value[i / 2] < value[i])) {
            swap(value, i / 2, i);
            i = i / 2;
        }
    }

    public int removeMax() {
        if (size == 0) {
            return -1;
        }
        int res = value[1];
        value[1] = value[size--]; // 将最后一个元素放到根节点位置 然后再自上而下地堆化 保证完全二叉树性质
        // 从顶点开始自上而下地堆化
        heapify(value, size, 1);
        return res;
    }

    /**
     * 保证以i为根节点的子树为大根堆
     * @param value
     * @param n 堆中的元素个数
     * @param i 自上而下堆化的节点位置
     */
    private void heapify(int[] value, int n, int i) {
        int maxPos;
        while (true) {
            // 从i 2*i 2*i+1中选出最小节点（同时注意子节点是否还在[1-n]范围内）
            maxPos = i;
            if (i * 2 <= n && value[i * 2] > value[i]) maxPos = i * 2;
            if (i * 2 + 1 <= n && value[i * 2 + 1] > value[maxPos]) maxPos = i * 2 + 1;
            // 当前节点比子节点大
            if (maxPos == i) {
                break;
            }
            swap(value, i, maxPos);
            i = maxPos;
        }
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
