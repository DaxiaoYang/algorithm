package com.algorithm.binarytree;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/15
 */
public class NextNode {

    int val;

    NextNode left;
    NextNode right;
    NextNode next;

    public NextNode(int val) {
        this.val = val;
    }

    public NextNode(int val, NextNode left, NextNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
