package com.algorithm.data_structure;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    private Node head = new Node();
    private Node tail = new Node();
    private int capacity;
    private Map<Integer, Node> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    // get 和 put都会影响次序
    public int get(int key) {
        // 存在则先更新位置关系
        if (map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            insert(node);
            return node.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        // 存在则先删除节点 插入节点统一放到后面
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        // 达到容量上限
        if (map.size() == capacity) {
            remove(tail.prev);
        }
        insert(new Node(key, value));
    }

    private void remove(Node node) {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insert(Node node) {
        map.put(node.key, node);
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private static class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node() {}

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}