package com.algorithm.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/18
 */
public class NMaxDepth {

//    public int maxDepth(Node root) {
//        if (root == null) {
//            return 0;
//        }
//        int max = 0;
//        if (root.children != null) {
//            for (Node child : root.children) {
//                max = Math.max(maxDepth(child), max);
//            }
//        }
//        return max + 1;
//    }
//
//    public int maxDepthBfs(Node root) {
//        if (root == null) {
//            return 0;
//        }
//        Queue<Node> queue = new LinkedList<>();
//        queue.offer(root);
//        Node curr;
//        int rowSize;
//        int depth = 0;
//        while (!queue.isEmpty()) {
//            rowSize = queue.size();
//            for (int i = 0; i < rowSize; i++) {
//                curr = queue.poll();
//                if (curr.children != null) {
//                    for (Node child : curr.children) {
//                        queue.offer(child);
//                    }
//                }
//            }
//            depth++;
//        }
//        return depth;
//    }
}
