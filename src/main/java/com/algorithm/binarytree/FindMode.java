package com.algorithm.binarytree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/7/4
 */
public class FindMode {

    /** 当前遍历到的值 */
    private int currVal;

    /** 当前遍历到的值的出现次数 */
    private int currCount;

    /** 当前遍历到的众数的出现次数 */
    private int maxCount;

    /** 当前遍历到的众数的数量 */
    private int modCount;

    /** 众数 */
    private int[] modes;

    public int[] findModeInSpace(TreeNode root) {
        // 第一次遍历找到：1.众数对应频次 2.众数的个数
        inorder(root);
        modes = new int[modCount];
        currVal = 0;
        currCount = 0;
        modCount = 0;
        // 第二次遍历：将对应众数频次的树加入众数list中
        inorder(root);
        return modes;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        handleVal(root.val);
        inorder(root.right);
    }

    private void handleVal(int val) {
        // 重置当前数的值和频次
        if (val != currVal) {
            currVal = val;
            currCount = 0;
        }
        currCount++;
        if (currCount > maxCount) {
            // 重置众数的频次和数量
            maxCount = currCount;
            modCount = 1;
        } else if (currCount == maxCount) {
            // modes在第二次遍历时不为空
            if (modes != null) {
                modes[modCount] = currVal;
            }
            // 更新众数的数量
            modCount++;
        }
    }


    public int[] findMode(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        TreeNode prev = null;
        int count = 0;
        int maxCount = 0;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (prev == null) {
                count = 1;
            } else if (prev.val != curr.val) {
                count = 1;
            } else {
                count++;
            }
            if (count >= maxCount) {
                if (count > maxCount) {
                    res.clear();
                }
                maxCount = count;
                res.add(curr.val);
            }
            prev = curr;
            curr = curr.right;
        }
        int[] ret = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }
        return ret;
    }
}
