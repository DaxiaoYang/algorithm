package com.algorithm.sort;

import java.util.*;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/8
 */
public class BucketSort {

    public int[] topKFrequent(int[] nums, int k) {
        // 出现的数字没有一个固定的范围 但是数字出现的频率的范围是确定的 即[0,len] 所以可以用桶排序
        List<Integer>[] buckets = new List[nums.length + 1];
        // 统计出 num -> frequency
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        // 遍历map 将数字挂到其对应的出现频率的桶上去
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            int num = entry.getKey();
            int frequency = entry.getValue();
            if (buckets[frequency] == null) {
                buckets[frequency] = new LinkedList<>();
            }
            buckets[frequency].add(num);
        }
        List<Integer> res = new ArrayList<>(k);
        // 从高频率往低频率遍历 统计结果
        for (int i = buckets.length - 1; i >= 0 && res.size() < k; i--) {
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
