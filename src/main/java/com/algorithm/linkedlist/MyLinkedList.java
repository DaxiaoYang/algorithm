package com.algorithm.linkedlist;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/19
 */
class MyLinkedList {

    private Node head = new Node(0);

    private Node tail = new Node(0);

    private int size;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        head.next = tail;
        tail.prev = head;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (!checkGetIndex(index)) {
            return -1;
        }
        return getNodeAtIndex(index).val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        // 复用
        addAtIndex(0 ,val);
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (!checkInsertIndex(index)) {
            return;
        }
        // 取出插入节点位置的后面一个节点的引用
        Node curr;
        if (index == size) {
            curr = tail;
        } else {
            curr = getNodeAtIndex(index);
        }
        Node nodeToAdd = new Node(val, curr.prev, curr);
        curr.prev.next = nodeToAdd;
        curr.prev = nodeToAdd;
        size++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (!checkGetIndex(index)) {
            return;
        }
        Node curr = getNodeAtIndex(index);
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        size--;
    }

    private Node getNodeAtIndex(int index) {
        Node curr = head.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    private boolean checkGetIndex(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        return true;
    }

    private boolean checkInsertIndex(int index) {
        if (index < 0 || index > size) {
            return false;
        }
        return true;
    }

    private static class Node {

        int val;

        Node prev;

        Node next;

        public Node(int val) {
            this(val, null, null);
        }

        public Node(int val, Node prev, Node next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
        System.out.println(myLinkedList.get(1));
        myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
        System.out.println(myLinkedList.get(1));
    }
}
