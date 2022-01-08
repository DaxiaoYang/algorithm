package com.algorithmlesson.hashmap;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/1
 */
public class MyHashMap {

    private int capacity = 16;

    private float loadFactor = 0.75f;

    private int size;

    private Node[] table;

    public MyHashMap() {
        table = new Node[capacity];
    }

    public void put(int key, int value) {
        resize();
        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new Node(key, value);
        } else {
            Node curr = table[index];
            while (curr != null) {
                if (curr.key == key) {
                    curr.val = value;
                    return;
                }
                curr = curr.next;
            }
            // insert at head
            Node newNode = new Node(key, value, table[index]);
            table[index] = newNode;
        }
        size++;
    }

    public int get(int key) {
        int index = getIndex(key);
        if (table[index] == null) {
            return -1;
        }
        Node curr = table[index];
        while (curr != null) {
            if (curr.key == key) {
                return curr.val;
            }
            curr = curr.next;
        }
        return -1;
    }

    public void remove(int key) {
        int index = getIndex(key);
        if (table[index] == null) {
            return;
        }
        if (table[index].key == key) {
            table[index] = table[index].next;
            size--;
            return;
        }
        Node prev = table[index];
        Node curr = table[index].next;
        while (curr != null) {
            if (curr.key == key) {
                prev.next = curr.next;
                size--;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    private int getIndex(int key) {
        return hash(key) & (capacity - 1);
    }

    private int hash(int key) {
        return (key >> 16) ^ key;
    }

    private void resize() {
        if (size < (int)(loadFactor * capacity)) {
            return;
        }
        int newCapacity = capacity << 1;
        Node[] newTable = new Node[newCapacity];
        for (int i = 0; i < capacity; i++) {
            if (table[i] == null) {
                continue;
            }
            Node oldHead = null;
            Node oldTail = null;
            Node newHead = null;
            Node newTail = null;
            Node curr = table[i];
            Node next = null;
            while (curr != null) {
                next = curr.next;
                curr.next = null;
                // node at this slot will be separated into two list,
                // one at its original slot and one at higher slot
                // depending on its bit value at capacity position
                if ((hash(curr.key) & capacity) == capacity) {
                    if (newHead == null) {
                        newHead = curr;
                        newTail = newHead;
                    } else {
                        newTail.next = curr;
                        newTail = curr;
                    }
                } else {
                    if (oldHead == null) {
                        oldHead = curr;
                        oldTail = oldHead;
                    } else {
                        oldTail.next = curr;
                        oldTail = curr;
                    }
                }
                curr = next;
            }
            newTable[i] = oldHead;
            newTable[i + capacity] = newHead;
        }
        table = newTable;
        capacity = newCapacity;
    }

    private static class Node {

        int key;

        int val;

        Node next;

        Node(int key, int val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
