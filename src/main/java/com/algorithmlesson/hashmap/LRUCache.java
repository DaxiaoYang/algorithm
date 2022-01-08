package com.algorithmlesson.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/2
 */
public class LRUCache {

    private Map<Integer, Node> map = new HashMap<>();

    private Node head = new Node(-1, -1);

    private Node tail = new Node(-1, -1);

    private int size;

    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(2);
        lru.put(1,1);
        lru.put(2,2);
        System.out.println(lru.get(1));
        lru.put(3,3);
        System.out.println(lru.get(2));
        lru.put(4,4);
        System.out.println(lru.get(1));
        System.out.println(lru.get(3));
        System.out.println(lru.get(4));

    }

    public int get(int key) {
        Node node = null;
        if ((node = map.get(key)) == null) {
            return -1;
        }
        removeNode(node);
        addNodeAtHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = null;
        if ((node = map.get(key)) != null) {
            removeNode(node);
            addNodeAtHead(node);
            node.value = value;
            return;
        }
        if (size == capacity) {
            // 先从map中remove掉key 以为你removeNode后 tail.prev会变化
            map.remove(tail.prev.key);
            removeNode(tail.prev);
            size--;
        }
        node = new Node(key, value);
        addNodeAtHead(node);
        map.put(key, node);
        size++;
    }

    private void addNodeAtHead(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    private void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private static class Node {

        int key;

        int value;

        Node prev;

        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        Node(int key, int value, Node prev, Node next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
