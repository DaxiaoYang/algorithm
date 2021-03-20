package com.algorithm.data_structure;

public class BinarySearchTree {

    private Node root;

    public int get(int key) {
        Node curr = root;
        while (curr != null) {
            if (key < curr.key) {
                curr = curr.left;
            } else if (key > curr.key) {
                curr = curr.right;
            } else {
                return curr.val;
            }
        }
        return -1;
    }

    public void insert(int key, int val) {
        if (root == null) {
            root = new Node(key, val);
            return;
        }
        Node curr = root;
        while (curr != null) {
            if (key < curr.key) {
                if (curr.left == null) {
                    curr.left = new Node(key, val);
                    return;
                }
                curr = curr.left;
            } else {
                if (curr.right == null) {
                    curr.right = new Node(key, val);
                    return;
                }
                curr = curr.right;
            }
        }
    }

    // 暂时假设二叉树中没有重复元素 可用拉链法解决重复元素问题
    public void delete(int key) {
        // 先找到待删除节点 删除操作也需要拿到待删除节点的父节点
        Node curr = root, parent = null;
        while (curr != null) {
            if (key > curr.key) {
                parent = curr;
                curr = curr.right;
            } else if (key < curr.key){
                parent = curr;
                curr = curr.left;
            } else {
                break;
            }
        }
        if (curr == null) {
            return;
        }
        // 若待删除的节点有两个子节点
        if (curr.left != null && curr.right != null) {
            // 找到中序遍历的后继(或者前驱)
            Node next = curr.right, nextParent = curr;
            while (next.left != null) {
                nextParent = next;
                next = next.left;
            }
            // 替换值 将待删除的节点设为 后继节点 这样就将删除的节点有两个子节点的问题 转化 为删除的节点只有只有1个或者0个子节点的问题
            // 右子树中的最小节点一定没有左子节点 否则就不是最小节点
            curr.key = next.key;
            curr = next;
            parent = nextParent;
        }
        // 待删除的节点只有1个或者0个子节点: 父节点直接指向待删除节点的孩子节点（或null）
        // 找到其孩子节点
        Node child = curr.left == null ? curr.right : curr.left;
        // 若待删除的节点为根节点
        if (parent == null) {
            root = child;
            return;
        }
        if (parent.left == curr) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }


    private static class Node {
        int key;
        int val;
        Node left;
        Node right;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(2, 4);
        tree.insert(1, 1);
        tree.insert(3, 9);
        tree.insert(4, 16);
        System.out.println(tree.get(4));
        tree.delete(4);
        System.out.println(tree.get(4));
        for (int i = 1; i <= 3; i++) {
            tree.delete(i);
            System.out.println(tree.get(i));
        }
    }
}
