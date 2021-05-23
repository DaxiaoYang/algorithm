package com.algorithm.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/21
 */
public class Anagram {

    public boolean isAnagram(String s, String t) {
        Set<Integer> set = new HashSet<>();
        if (s.length() != t.length()) {
            return false;
        }
        // 元素 -> 元素出现的频次的映射 hash:对象 -> 某个范围内的整数
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            count[c1 - 'a']++;
            count[c2 - 'a']--;
        }
        for (int num : count) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }
}
