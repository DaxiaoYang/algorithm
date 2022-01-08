package com.algorithmlesson.binarytree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/7
 */
public class NaryTreePreorder {


    public static void main(String[] args) {
        Node root = new Node(1);
        Node child1 = new Node(3);
        Node child2 = new Node(2);
        Node child3 = new Node(4);
        Node child4 = new Node(5);
        Node child5 = new Node(6);
        root.children = Arrays.asList(child1, child2, child3);
        child1.children = Arrays.asList(child4, child5);
        System.out.println(preorder(root));
    }

    public static List<Integer> preorder(Node root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Deque<Frame> stack = new LinkedList<>();
        stack.push(new Frame(root));
        while (!stack.isEmpty()) {
            Frame top = stack.peek();
            if (top.status == 0) {
                res.add(top.node.val);
            }
            if (top.status < top.childSize) {
                stack.push(new Frame(top.node.children.get(top.status)));
                top.status++;
            } else {
                stack.pop();
            }
        }
        return res;
    }


    public List<Integer> postorder(Node root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Deque<Frame> stack = new LinkedList<>();
        stack.push(new Frame(root));
        while (!stack.isEmpty()) {
            Frame top = stack.peek();
            if (top.status < top.childSize) {
                stack.push(new Frame(top.node.children.get(top.status)));
                top.status++;
            } else {
                res.add(top.node.val);
                stack.pop();
            }
        }
        return res;
    }

    private static class Frame {
        Node node;
        int status;
        int childSize;

        Frame(Node node) {
            this.node = node;
            this.childSize = node.children == null ? 0 : node.children.size();
        }
    }
}
