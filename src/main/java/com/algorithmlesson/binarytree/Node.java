package com.algorithmlesson.binarytree;

import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/7
 */
public class Node {

    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
