package com.algorithm.data_structure;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {

    private int v;
    private List<Integer>[] adj;

    private boolean found;

    public Graph(int v) {
        this.v = v;
        adj = new List[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void add(int from, int to) {
        adj[from].add(to);
        adj[to].add(from);
    }

    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.add(0, 1);
        graph.add(1, 3);
        graph.add(0, 2);
        graph.add(2, 3);
//        graph.bfs(0,3);
        graph.dfs(0, 3);
    }

    public void dfs(int from, int to) {
        // 记录节点是否已经被访问
        boolean[] visited = new boolean[v];
        // 记录访问过程中每个节点的前驱
        int[] prev = new int[v];
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }
        dfs(from, to, prev, visited);
        print(prev, from, to);
    }

    private void dfs(int from, int to, int[] prev, boolean[] visited) {
        if (found) {
            return;
        }
        // 表明from节点已被访问
        visited[from] = true;
        // 找到目标节点
        if (from == to) {
            found = true;
            return;
        }
        // 遍历邻接节点
        for (int i = 0; i < adj[from].size(); i++) {
            // 找到任意一个未被访问的邻接节点
            int next = adj[from].get(i);
            if (!visited[next]) {
                // 记录前驱节点
                prev[next] = from;
                // 递归遍历邻接节点
                dfs(next, to, prev, visited);
            }
        }
    }

    /**
     * bfs求最短路径
     * @param from
     * @param to
     */
    public void bfs(int from, int to) {
        // 记录节点是否已经被访问
        boolean[] visited = new boolean[v];
        // 记录访问过程中每个节点的前驱
        int[] prev = new int[v];
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }
        // 队列中存储的是已经被访问过 但是其邻接节点还未被访问过的节点
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(from);
        visited[from] = true;
        while (!queue.isEmpty()) {
            Integer curr = queue.poll();
            // 遍历当前节点的所有邻接节点
            for (int i = 0; i < adj[curr].size(); i++) {
                int next = adj[curr].get(i);
                // 若未被访问过
                if (!visited[next]) {
                    // 置前驱节点
                    prev[next] = curr;
                    // 找到目标节点 递归打印轨迹
                    if (next == to) {
                        print(prev, from, to);
                        return;
                    }
                    // 置访问标志
                    visited[next] = true;
                    // 将其加入队列
                    queue.offer(next);
                }
            }
        }
    }

    // 递归打印from -> to的轨迹
    private void print(int[] prev, int from, int to) {
        // 当还存在前驱节点 且前驱节点不是起始节点的时候 递归打印
        if (prev[to] != -1 && from != to) {
            print(prev, from, prev[to]);
        }
        // 走到这里说明最上面的栈 to已经为起始节点
        System.out.println(to + " ");
    }
}
