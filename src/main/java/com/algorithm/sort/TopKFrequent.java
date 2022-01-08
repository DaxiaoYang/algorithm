package com.algorithm.sort;

import java.util.*;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/11/23
 */
public class TopKFrequent {


    public static int[] topKFrequentHeap(int[] nums, int k) {
        // 小顶堆求 频率最高的三个
        Map<Integer, Integer> num2Count = new HashMap<>();
        for (int num : nums) {
            num2Count.put(num, num2Count.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((e1, e2) -> e1.getValue() - e2.getValue());
        for (Map.Entry<Integer, Integer> entry : num2Count.entrySet()) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            ret[i] = queue.poll().getKey();
        }
        return ret;
    }

    public static int[] topKFrequent(int[] nums, int k) {
        // 桶排序
        Map<Integer, Integer> num2Count = new HashMap<>();
        for (int num : nums) {
            num2Count.put(num, num2Count.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] buckets = new List[nums.length + 1];
        int num;
        int count;
        for (Map.Entry<Integer, Integer> entry : num2Count.entrySet()) {
            num = entry.getKey();
            count = entry.getValue();
            if (buckets[count] == null) {
                buckets[count] = new ArrayList<>();
            }
            buckets[count].add(num);
        }
        List<Integer> res = new ArrayList<>(k);
        for (int i = nums.length; i > 0 && res.size() < k; i--) {
            if (buckets[i] != null) {
                res.addAll(buckets[i]);
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            ret[i] = res.get(i);
        }
        return ret;
    }
}
