package com.algorithm.stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/5
 * 后缀表达式计算
 */
public class EvaluateReversePolishNotation {

    /**
     * strategy design pattern: operator -> the function used for two input elements
     */
    private static Map<String, BiFunction<Integer, Integer, Integer>> operatorToOperation = new HashMap<>();

    static {
        operatorToOperation.put("+", (o1, o2) -> o1 + o2);
        operatorToOperation.put("-", (o1, o2) -> o1 - o2);
        operatorToOperation.put("*", (o1, o2) -> o1 * o2);
        operatorToOperation.put("/", (o1, o2) -> o1 / o2);
    }

    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        for (String token : tokens) {
            // meet a operator, pop two elements as operands
            if (operatorToOperation.containsKey(token)) {
                Integer operand2 = stack.pop();
                Integer operand1 = stack.pop();
                stack.push(operatorToOperation.get(token).apply(operand1, operand2));
            } else {
                // meet a operand, push it to stack
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

}
