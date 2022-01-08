package com.algorithmlesson.linkedlist;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/6
 *
 * 优化：
 * 1. 增加tail指针 降低在尾部添加的时间复杂度
 * 2. 增加虚拟头结点（哨兵） 将边界条件化入正常的处理流程
 */
public class MyLinkedList {

    /**
     * 哑结点
     */
    private Node head = new Node();

    private Node tail = head;

    private static class Node {

        private int data;

        private Node next;

        public Node() {}

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * 1. 遍历
     */
    public void traverse() {
        Node curr = head.next;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    /**
     * 2.查找
     */
    public Node find(int val) {
        Node curr = head;
        while (curr != null) {
            if (curr.data == val) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    /**
     * 3.插入：
     *   链表头部插入
     *   链表尾部插入
     *   给定结点后插入
     */

    public void insertHead(int val) {
//        head = new Node(val, head);
        head.next = new Node(val, head.next);
    }

    /**
     * 无tail指针
     */
    public Node insertTail(int val) {
        Node newNode = new Node(val);
//        if (head == null) {
//            head = newNode;
//        } else {
//            Node curr = head;
//            while (curr.next != null) {
//                curr = curr.next;
//            }
//            curr.next = newNode;
//        }
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
        return newNode;
    }

    /**
     * 有tail指针
     */
    public Node insertTailNode(int val) {
        Node newNode = new Node(val);
//        if (head == null) {
//            head = newNode;
//            tail = newNode;
//        } else {
//            tail.next = newNode;
//            tail = newNode;
//        }
        tail.next = newNode;
        tail = newNode;
        return newNode;
    }

    /**
     * 给定结点后插入
     */
    public void insertNode(Node node, int val) {
        if (node == null) {
            return;
        }
        Node newNode = new Node(val, node.next);
        node.next = newNode;
    }

    /**
     * 4 删除
     *   删除给定结点后的结点
     *   删除给定结点
     */

    public void deleteNode(Node node) {
        if (node == null) {
            return;
        }
        node.next = node.next.next;
    }

    public void deleteNode2(Node node) {
        Node curr = head.next;
        Node prev = head;
        while (curr != null && curr != node) {
            prev = curr;
            curr = curr.next;
        }
        if (curr == null) {
            return;
        }
        prev.next = curr.next;
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        Node node1 = list.insertTailNode(1);
        Node node2 = list.insertTailNode(2);
        Node node3 = list.insertTailNode(3);
//        list.traverse();

//        list.insertHead(0);
//        list.insertHead(-1);
//        list.insertHead(-2);
//        list.traverse();
        list.traverse();
        list.insertNode(node1, 6);
        list.traverse();
        list.deleteNode2(node3);
        list.traverse();
    }
}
