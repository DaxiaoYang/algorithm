package com.algorithm.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.algorithm.binarytree.*;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/14
 */
public class NaryTreeLevelOrder {

//    public List<List<Integer>> levelOrder(Node root) {
//        List<List<Integer>> rows = new ArrayList<>();
//        if (root == null) {
//            return rows;
//        }
//        Queue<Node> queue = new LinkedList<>();
//        queue.offer(root);
//        Node curr;
//        int rowSize;
//        while (!queue.isEmpty()) {
//            rowSize = queue.size();
//            List<Integer> row = new ArrayList<>(rowSize);
//            for (int i = 0; i < rowSize; i++) {
//                curr = queue.poll();
//                row.add(curr.val);
//                for (Node child : curr.children) {
//                    queue.offer(child);
//                }
//            }
//            rows.add(row);
//        }
//        return rows;
//    }
//
//    public List<List<Integer>> levelOrderDfs(Node root) {
//        List<List<Integer>> rows = new ArrayList<>();
//        dfs(rows, root, 0);
//        return rows;
//    }
//
//    private void dfs(List<List<Integer>> rows, Node root, int depth) {
//        if (root == null) {
//            return;
//        }
//        if (rows.size() == depth) {
//            rows.add(new LinkedList<>());
//        }
//        rows.get(depth).add(root.val);
//        for (Node child : root.children) {
//            dfs(rows, child, depth + 1);
//        }
//    }
}
