package com.algorithm.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/3
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if ('(' == c) stack.push(')');
            else if ('{' == c) stack.push('}');
            else if ('[' == c) stack.push(']');
            else if (stack.isEmpty() || !stack.pop().equals(c)) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
