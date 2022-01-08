package com.algorithm.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/26
 */
public class MinWindow {

    public String minWindow(String s, String t) {
        int minLen = Integer.MAX_VALUE, start = 0, end = 0;
        // [start, end] 滑动窗口中没有出现的t中的字符的个数
        int numToFind = t.length();
        int minStart = 0;
        int[] map = new int[128];
        // 建立映射 map中 [] 表示 t的[]字符个数 - 滑动窗口中[]字符个数 的差
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i)]++;
        }
        // 扩大窗口
        while (end < s.length()) {
            if (map[s.charAt(end)] > 0) {
                numToFind--;
            }
            map[s.charAt(end)]--;
            end++;
            // 满足条件时 缩小窗口
            while (numToFind == 0) {
                if (end - start < minLen) {
                    minStart = start;
                    minLen = end - start;
                }
                map[s.charAt(start)]++;
                if (map[s.charAt(start)] > 0) {
                    numToFind++;
                }
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}
