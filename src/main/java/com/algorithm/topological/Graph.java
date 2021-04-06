package com.algorithm.topological;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {

    int v;
    List<Integer>[] adj;

    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.topologicKahn();
        System.out.println();
        graph.topologicDfs();
    }

    public Graph(int v) {
        this.v = v;
        adj = new List[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int from, int to) {
        adj[from].add(to);
    }

    // Kahn算法实现邻接表
    public void topologicKahn() {
        // 每个节点的入度
        int[] indegree = new int[v];
        // 遍历整个图 构建每个节点的入度
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                indegree[adj[i].get(j)]++;
            }
        }
        // 将入度为0的节点入队 即拓扑排序的开始节点
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        // 遍历完所有入度为0的节点
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            System.out.print("-> " + curr);
            // 将当前节点"删除"：将其后继节点的入度-1
            for (int i = 0; i < adj[curr].size(); i++) {
                int j = adj[curr].get(i);
                // 如果有新的入度为0的节点 加入队列
                if (--indegree[j] == 0) {
                    queue.offer(j);
                }
            }
        }
    }

    // dfs实现拓扑排序
    public void topologicDfs() {
        // 构造一个逆邻接表
        LinkedList<Integer>[] reversedAdj = new LinkedList[v];
        // 初始化 不然之后会报空指针
        for (int i = 0; i < v; i++) {
            reversedAdj[i] = new LinkedList<>();
        }
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                // 注意这里链表中节点的值才是节点
                reversedAdj[adj[i].get(j)].add(i);
            }
        }
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                visited[i] = true;
                // dfs将i节点以及其所有前驱节点输出 prev -> prev -> i
                dfs(i, reversedAdj, visited);
            }
        }
    }

    private void dfs(int vertex, LinkedList<Integer>[] reversedAdj, boolean[] visited) {
        // 遍历其所有前驱节点
        for (int i = 0; i < reversedAdj[vertex].size(); i++) {
            int prev = reversedAdj[vertex].get(i);
            // 如果其前驱节点没有被访问过 递归访问其前驱节点
            if (!visited[prev]) {
                visited[prev] = true;
                dfs(prev, reversedAdj, visited);
            }
        }
        // 最后再输出自己
        System.out.print("--> " + vertex);
    }
}
