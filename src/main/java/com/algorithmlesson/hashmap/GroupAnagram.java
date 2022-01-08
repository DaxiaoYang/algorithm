package com.algorithmlesson.hashmap;

import java.util.*;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/31
 */
public class GroupAnagram {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(strs));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = getKey(str);
            if (map.get(key) == null) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    private static String getKey(String s) {
        // 因为每个字符串的大小 <= 100 char <= 127 可以统计每个字符出现的个数
        char[] count = new char[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        // 返回的其实是乱码 但是可以唯一标识一组异位同构词
        return String.valueOf(count);
    }
}
