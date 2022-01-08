package com.algorithm.sort;

import java.util.Arrays;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/11/2
 */
public class SortChar {

    public static void main(String[] args) {
        char[] chars = {'D', 'a', 'F', 'B', 'c', 'A', 'z'};
        sortChar(chars);
        System.out.println(Arrays.toString(chars));
    }

    /**
     * 将字符数组中所有小写的字母都放在大写字母的前面
     * 双指针法
     */
    public static void sortChar(char[] chars) {
        int left = 0, right = chars.length - 1;
        while (true) {
            while (left < right && Character.isLowerCase(chars[left])) {
                left++;
            }
            while (right > left && Character.isUpperCase(chars[right])) {
                right--;
            }
            if (left >= right) {
                break;
            }
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
        }
    }
}
