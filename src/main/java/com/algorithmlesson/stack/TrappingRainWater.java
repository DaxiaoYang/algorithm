package com.algorithmlesson.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/18
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
//        System.out.println(trap(height));
        System.out.println(trap2(height));
    }

    public static int trap(int[] height) {
        // 存放下标
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            // 栈空直接放入 栈中存放的高度从下往上递减
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            // 当前高度
            int curr = height[i];
            while (!stack.isEmpty()) {
                int top = stack.peek();
                // 满足递减 直接放入
                if (curr < height[top]) {
                    stack.push(i);
                    break;
                } else if (curr == height[top]) {
                    // 等于的话 更新下标
                    stack.pop();
                    stack.push(i);
                    break;
                } else {
                    // 当前高度大于最小高度 说明找到了一个槽
                    int mid = stack.pop();
                    // 如果没有凹
                    if (stack.isEmpty()) {
                        stack.push(i);
                        break;
                    }
                    // 挨个计算
                    int left = stack.peek();
                    int h = Math.min(height[left], curr) - height[mid];
                    int w = i - left - 1;
                    res += h * w;
                }
            }
        }
        return res;
    }

    public static int trap2(int[] height) {
        // 使用单调栈是为了找到每个柱子两边第一个大于该柱子高度的柱子
        Deque<Integer> stack = new ArrayDeque<>();
        int sum = 0;
        // height.length >= 1 所以直接push 0下标
        stack.push(0);
        for (int i = 1; i < height.length; i++) {
            // 递减 直接放入
            if (height[i] < height[stack.peek()]) {
                stack.push(i);
                continue;
            }
            // 相等 刷新下标 因为计算左柱子是用相邻相同柱子的最右边的一根
            if (height[i] == height[stack.peek()]) {
                stack.pop();
                stack.push(i);
                continue;
            }
            // 发现凹槽 依次弹出并计算
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                // 凹槽底部
                int mid = stack.pop();
                // 没有左柱子
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int h = Math.min(height[left], height[i]) - height[mid];
                int w = i - left - 1;
                sum += h * w;
            }
            stack.push(i);
        }
        return sum;
    }
}
