package com.algorithm.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/4
 */
public class RemoveDuplicatesInString {

    /**
     * 初版
     * @param s
     * @return
     */
    public String removeDuplicates(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().equals(c)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.insert(0, stack.pop());
        }
        return res.toString();
    }

    /**
     * 用StringBuilder表示栈
     * @param s
     * @return
     */
    public String removeDuplicates2(String s) {
        StringBuilder res = new StringBuilder(s.length());
        int len;
        for (char c : s.toCharArray()) {
            len = res.length();
            if (len != 0 && res.charAt(len - 1) == c) {
                res.setLength(len - 1);
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    /**
     * 双指针法
     * @param s
     * @return
     */
    public String removeDuplicatesTwoPointers(String s) {
        char[] res = s.toCharArray();
        int j = 0;
        for (int i = 0; i < res.length; i++, j++) {
            res[j] = res[i];
            if (j > 0 && res[j] == res[j - 1]) {
                j -= 2;
            }
        }
        return new String(res, 0, j);
    }
}
