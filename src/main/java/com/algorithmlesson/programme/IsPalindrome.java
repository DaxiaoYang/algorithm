package com.algorithmlesson.programme;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/11/29
 */
public class IsPalindrome {

    public static void main(String[] args) {
//        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(String.valueOf(Integer.MAX_VALUE).length());
    }

    public static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        char left;
        char right;
        final char distance = 'a' - 'A';
        while (true) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            if (i >= j) {
                break;
            }
            left = s.charAt(i);
            right = s.charAt(j);
            // 两个数比完了
            i++;
            j--;
            if (left != right) {
                if (Character.isLetter(left) && Character.isLetter(right) && Math.abs(right - left) == distance) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }
}
