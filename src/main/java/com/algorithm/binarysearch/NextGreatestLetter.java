package com.algorithm.binarysearch;

/**
 * @author siping.yang@appshahe.com
 * @date 2023/10/13 9:17
 */
public class NextGreatestLetter {

    public static void main(String[] args) {
        char[] letters = {'x', 'x', 'y', 'y'};
        char z = nextGreatestLetter(letters, 'z');
        System.out.println(z);
    }
    public static char nextGreatestLetter(char[] letters, char target) {
        int low = 0, high = letters.length - 1, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (letters[mid] > target) {
                if (mid == 0 || letters[mid - 1] <= target) {
                    return letters[mid];
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        // 想想不存在的时候应该返回什么
        return letters[0];
    }
}
