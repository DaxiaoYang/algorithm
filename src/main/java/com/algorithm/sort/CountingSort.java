package com.algorithm.sort;

import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        int[] a = {2,5,3,0,2,3,0,3};
        countSort2(a);
        System.out.println(Arrays.toString(a));

//        char[] c = {'a', '1', 'B', 'b', 'A', '2'};
//        sortChar(c);
//        System.out.println(Arrays.toString(c));
    }

    /**
     * 要求数组中的元素为非负数 且范围不能太大
     */
    public static void countingSort(int[] a) {
       // 确定范围
       int max = a[0];
       int len = a.length;
        for (int i = 1; i < len; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        // 计算每个元素的个数
        int[] count = new int[max + 1];
        for (int num : a) {
            count[num]++;
        }
        // 累加个数 count[i]中的值为 <=i的数个数
        for (int i = 1; i <= max; i++) {
            count[i] = count[i - 1] + count[i];
        }
        int[] temp = new int[len];
        // 从后向前遍历 将元素插入到相应位置
        for (int i = len - 1; i >= 0; i--) {
            temp[--count[a[i]]] = a[i];
        }
        System.arraycopy(temp, 0, a, 0, len);
    }

    /**
     * 假设我们现在需要对 D，a，F，B，c，A，z 这个字符串进行排序，
     * 要求将其中所有小写字母都排在大写字母的前面，
     * 但小写字母内部和大写字母内部不要求有序。
     * 比如经过排序之后为 a，c，z，D，F，B，A，这个如何来实现呢？
     * 如果字符串中存储的不仅有大小写字母，还有数字。
     * 要将小写字母的放到前面，大写字母放在最后，数字放在中间，
     * 不用排序算法，又该怎么解决呢？
     */
    public static void sortChar(char[] c) {
        // 双指针
        int i = 0, j = c.length - 1;
        while (i < j) {
            while (Character.isLowerCase(c[i])) {
                i++;
            }
            while (!Character.isLowerCase(c[j])) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(c, i, j);
            i++;
            j--;
        }
        j = c.length - 1;
        while (i < j) {
            while (Character.isDigit(c[i])) {
                i++;
            }
            while (Character.isUpperCase(c[j])) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(c, i, j);
            i++;
            j--;
        }
    }

    public static void swap(char[] c, int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }



    private static void countSort2(int[] nums) {
        // 假设nums的范围为 [0, max] 若存在负数则需要加上偏移量 若为小数则可以乘以10的倍数 转化为非负整数
        int max = 0;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        int[] count = new int[max + 1];
        for (int num : nums) {
            count[num]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        int[] temp = new int[nums.length];
        int index;
        for (int i = nums.length - 1; i >= 0; i--) {
            index = count[nums[i]] - 1;
            temp[index] = nums[i];
            count[nums[i]]--;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];
        }
    }
}
