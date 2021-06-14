package com.algorithm.binarytree;

import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/14
 */
public class Node {

    public int val;

    public List<Node> children;

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}
