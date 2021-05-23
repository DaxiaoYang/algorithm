package com.algorithm.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/21
 */
public class IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        for (int num : nums2) {
            if (set.contains(num)) {
                res.add(num);
            }
        }
        int[] ret = new int[res.size()];
        int i = 0;
        for (int num : res) {
            ret[i++] = num;
        }
        return ret;
    }
}
