package com.algorithmlesson.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/19
 */
public class LargestRectangleinHistogram {

    public int largestRectangleArea(int[] heights) {
        // 与接雨水思路相同
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        stack.push(0);
        // 数组前后加0 哨兵 是为了处理边界条件 前面的一个0 可以处理第一个元素 后面的0 可以把之前所有的高度都逼出来
        int[] extend = new int[heights.length + 2];
        for (int i = 1; i < extend.length - 1; i++) {
            extend[i] = heights[i - 1];
        }
        for (int i = 1; i < extend.length; i++) {
            if (extend[i] < extend[stack.peek()]) {
                // 自底向上递增 所以能找到当前柱子左边和右边第一个小于当前柱子高度的柱子
                stack.push(i);
            } else if (extend[i] == extend[stack.peek()]) {
                // 相同也放进去 遇到右边的低柱子的时候 两个都计算
                stack.push(i);
            } else {
                while (!stack.isEmpty() && extend[i] < extend[stack.peek()]) {
                    int mid = stack.pop();
                    if (stack.isEmpty()) {
                        break;
                    }
                    int left = stack.peek();
                    int h = extend[mid];
                    int w = i - left - 1;
                    res = Math.max(res, h * w);
                }
                stack.push(i);
            }
        }
        return res;
    }
}
