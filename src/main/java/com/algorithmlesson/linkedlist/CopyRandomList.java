package com.algorithmlesson.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/13
 */
public class CopyRandomList {

    public RandomNode copyRandomList(RandomNode head) {
        // 空间复杂度O(n)
        // 旧链表结点 -> 新链表结点
        Map<RandomNode, RandomNode> original2Copy = new HashMap<>();
        RandomNode curr = head;
        // 第一次遍历 先创建新链表结点
        while (curr != null) {
            original2Copy.put(curr, new RandomNode(curr.val));
            curr = curr.next;
        }
        curr = head;
        RandomNode copyRandomNode;
        // 第二次遍历 赋值next和random指针
        while (curr != null) {
            copyRandomNode = original2Copy.get(curr);
            copyRandomNode.next = original2Copy.get(curr.next);
            copyRandomNode.random = original2Copy.get(curr.random);
            curr = curr.next;
        }
        return original2Copy.get(head);
    }

    public RandomNode copyRandomList2(RandomNode head) {
        // O(1) 空间复杂度 直接把新生成的链表结点放在每个被复制的链表结点后 来保证原始结点与新结点的对应关系
        RandomNode curr = head;
        RandomNode copy;
        RandomNode next;
        // 复制结点
        while (curr != null) {
            copy = new RandomNode(curr.val);
            copy.next = curr.next;
            next = curr.next;
            curr.next = copy;
            curr = next;
        }
        curr = head;
        // 复制random指针
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }
        RandomNode dummy = new RandomNode(0);
        RandomNode tail = dummy;
        curr = head;
        // 剥离新链表 还原原始链表
        while (curr != null) {
            copy = curr.next;
            next = curr.next.next;
            copy.next = null;
            tail.next = copy;
            tail = copy;
            curr.next = next;
            curr = next;
        }
        return dummy.next;
    }

    private static class RandomNode {
        int val;
        RandomNode next;
        RandomNode random;

        public RandomNode(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
