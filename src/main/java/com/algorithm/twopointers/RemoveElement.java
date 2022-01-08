package com.algorithm.twopointers;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/16
 */
public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        // 实际上是快慢指针 遍历数组的指针是快指针 len是慢指针
        // 生成的数组插入位置的指针
        int len = 0;
        for (int num : nums) {
            // 将符合条件的元素加入到数组中
            if (num != val) {
                nums[len++] = num;
            }
        }
        return len;
    }
}
