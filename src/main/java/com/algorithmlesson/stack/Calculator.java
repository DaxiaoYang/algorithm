package com.algorithmlesson.stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/16
 */
public class Calculator {

    private static Map<Character, Integer> priority = new HashMap<>();

    static {
        priority.put('+', 0);
        priority.put('-', 0);
        priority.put('*', 1);
        priority.put('/', 1);
    }

    public static void main(String[] args) {
        System.out.println(calculate("3+2*2"));
    }

    public static int calculate(String s) {
        Deque<Integer> operand = new LinkedList<>();
        Deque<Character> operator = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            // 是数字则拼接
            if (Character.isDigit(c)) {
                int num = 0;
                int j;
                for (j = i; j < s.length() && Character.isDigit(s.charAt(j)); j++) {
                    num = 10 * num + s.charAt(j) - '0';
                }
                operand.push(num);
                // 后面i会自动++
                i = j - 1;
                continue;
            }
            // 当操作符栈为空 或 当前操作符优先级较大（所以要优先计算 放到更上面的位置）
            if (operator.isEmpty() || priority.get(c) > priority.get(operator.peek())) {
                operator.push(c);
                continue;
            }
            // 当前操作符优先级 <= top 把操作符逼出来进行计算
            while (!operator.isEmpty() && priority.get(c) <= priority.get(operator.peek())) {
                calculate(operand, operator);
            }
            // 全部逼完之后 再push
            operator.push(c);
        }
        // 遍历完后 处理未处理的操作符
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
