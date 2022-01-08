package com.algorithmlesson.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/19
 */
public class Calculator3 {

    private static Map<Character, Integer> priority = new HashMap<>();

    static {
        // 运算符优先级
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('*', 2);
        priority.put('/', 2);
        // 括号优先级最高
        priority.put('(', 3);
    }

    public static void main(String[] args) {
        System.out.println(calculate("2*(5+5*2)/3+(6/2+8)"));
    }

    public static int calculate(String s) {
        Deque<Integer> operand = new ArrayDeque<>();
        // 这个操作符栈本质上也是一个单调栈 栈内元素的优先级↑依次增高
        Deque<Character> operator = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                int num = 0;
                int j;
                for (j = i; j < s.length() && Character.isDigit(s.charAt(j)); j++) {
                    num = num * 10 + s.charAt(j) - '0';
                }
                operand.push(num);
                i = j - 1;
                continue;
            }
            // 遇到一个) 则逼出一个左括号
            if (c == ')') {
                while (operator.peek() != '(') {
                    calculate(operand, operator);
                }
                operator.pop();
                continue;
            }
            // 当当前运算符优先级较低时 弹出操作符中栈顶的操作符进行计算
            while (!operator.isEmpty() && operator.peek() != '(' && priority.get(operator.peek()) >= priority.get(c)) {
                calculate(operand, operator);
            }
            operator.push(c);
        }
        // 处理剩下的操作符
        while (!operator.isEmpty()) {
            calculate(operand, operator);
        }
        return operand.pop();
    }

    private static void calculate(Deque<Integer> operand, Deque<Character> operator) {
        int num2 = operand.pop();
        int num1 = operand.pop();
        char c = operator.pop();
        if (c == '+') {
            operand.push(num1 + num2);
        } else if (c == '-') {
            operand.push(num1 - num2);
        } else if (c == '*') {
            operand.push(num1 * num2);
        } else if (c == '/') {
            operand.push(num1 / num2);
        }
    }
}
