package com.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {

    public static void main(String[] args) {
        System.out.println(addOperators("105", 5));
    }

    public static List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        dfs(0, 0, 0, new StringBuilder(), target, num, res);
        return res;
    }

    /**
     *
     * @param pos 当前所处的位置
     * @param prev 当前已经计算的值 用long型避免溢出的问题
     * @param multi 用于乘法计算的值
     */
    private static void dfs(int pos, long prev, long multi, StringBuilder expr, int target, String num, List<String> res) {
        if (pos == num.length()) {
            if (target == prev) {
                res.add(expr.toString());
            }
            return;
        }
        int len = expr.length();
        for (int i = pos; i < num.length(); i++) {
            // 避免0123 这种不是数字的数
            if (num.charAt(pos) == '0' && i != pos) {
                return;
            }
            // 取出当前遍历的数
            long curr = Long.parseLong(num.substring(pos, i + 1));
            // 若为第一个数
            if (pos == 0) {
                dfs(i + 1, prev + curr, curr, expr.append(curr), target, num, res);
                // 用setlength的方式 移动末尾指针
                expr.setLength(len);
            } else {
                // 关键在prev 和 multi的计算
                dfs(i + 1, prev + curr, curr, expr.append('+').append(curr), target, num, res);
                expr.setLength(len);
                dfs(i + 1, prev - curr, -curr, expr.append('-').append(curr), target, num, res);
                expr.setLength(len);
                // for example, if you have a sequence of 12345 and you have proceeded to 1 + 2 + 3,
                // now your eval is 6 right? If you want to add a * between 3 and 4, you would take 3 as the digit to be multiplied,
                // so you want to take it out from the existing eval. You have 1 + 2 + 3 * 4 and the eval now is (1 + 2 + 3) - 3 + (3 * 4).
                dfs(i + 1, prev - multi + multi * curr, curr * multi, expr.append('*').append(curr), target, num, res);
                expr.setLength(len);
            }
        }
    }
}
