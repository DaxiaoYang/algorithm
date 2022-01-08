package com.algorithmlesson.recursive;

import com.algorithm.linkedlist.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/21
 */
public class ReversePrint {

    public static void main(String[] args) {
//        ListNode head = new ListNode(1, new ListNode(3, new ListNode(2)));
//        reversePrint(head);
        int min = Integer.MIN_VALUE;
        System.out.println(min);
        System.out.println(-min);
    }

    public static int[] reversePrint(ListNode head) {
        List<Integer> res = new ArrayList<>();
        reversePrint(head, res);
        int[] ret = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }
        return ret;
    }

    private static void reversePrint(ListNode head, List<Integer> res) {
        if (head == null) {
            return;
        }
        reversePrint(head.next, res);
        res.add(head.val);
    }
}
