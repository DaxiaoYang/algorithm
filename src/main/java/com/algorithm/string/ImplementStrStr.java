package com.algorithm.string;

import java.util.Arrays;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/30
 * 单模式字符串匹配：Sunday算法
 */
public class ImplementStrStr {


    public static void main(String[] args) {
        System.out.println(strStr("substringsearching", "search"));
        // 下标5之前这部分的字符串（也就是字符串aabaa）的最长相等的前缀 和 后缀字符串是 子字符串aa ，
        // 因为找到了最长相等的前缀和后缀，匹配失败的位置是后缀子串的后面，那么我们找到与其相同的前缀的后面从新匹配就可以了。
        System.out.println(strStrKMP("aabaabaafa", "aabaaf"));
    }

    public static int strStr(String haystack, String needle) {
        // 子串为空 返回0
        if ("".equals(needle)) {
            return 0;
        }
        char[] text = haystack.toCharArray();
        char[] pattern = needle.toCharArray();
        int tLen = text.length;
        int pLen = pattern.length;
        // 匹配失败时 主串中匹配的字符串的后面一位字符在move中的值就是本次需要移动距离
        int[] move = new int[128];
        // 初始化移动数组 默认为字符在子串中都不存在 所以移动距离 = 子串长度 + 1
        Arrays.fill(move, pLen + 1);
        // 移动距离 = 子串长度 - 下标（以最右出现的为准）
        for (int i = 0; i < pLen; i++) {
            move[pattern[i]] = pLen - i;
        }
        int i = 0, j;
        // 剪枝
        while (i + pLen <= tLen) {
            j = 0;
            while (text[i + j] == pattern[j]) {
                j++;
                if (j >= pLen) {
                    return i;
                }
            }
            // 处理特殊情况 避免下面下标越界
            if (i + pLen == tLen) {
                break;
            }
            // 找到本次移动的距离
            i += move[text[i + pLen]];
        }
        return -1;
    }


    public static int strStrKMP(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        char[] text = haystack.toCharArray();
        char[] pattern = needle.toCharArray();
        int tLen = text.length;
        int pLen = pattern.length;
        int[] next = getNext(pattern);
        int j = 0;
        for (int i = 0; i < tLen; i++) {
            // 匹配失败 在next数组中找到响应的回退位置
            while (j > 0 && text[i] != pattern[j]) {
                j = next[j - 1];
            }
            // 匹配成功i j都往后移动
            if (text[i] == pattern[j]) {
                j++;
            }
            if (j == pLen) {
                return i - pLen + 1;
            }
        }
        return -1;
    }

    /**
     * https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0028.%E5%AE%9E%E7%8E%B0strStr.md
     * 获取前缀表（next数组）在主串和子串匹配失败的时候，找到子串应该回退的位置
     * @param pattern 模式串
     */
    public static int[] getNext(char[] pattern) {
        int[] next = new int[pattern.length];
        // j为前缀的起点 i为后缀的起点
        int j = 0;
        // i从1开始 因为单个字符没有相同的前后缀
        for (int i = 1; i < pattern.length; i++) {
            // 不匹配则回退
            while (j > 0 && pattern[i] != pattern[j]) {
                // 取前面子串的最长相同前后缀长度
                j = next[j - 1];
            }
            // 相同则都往后移动
            if (pattern[i] == pattern[j]) {
                j++;
            }
            // 赋值当前的最长相同前后缀 [0,i]
            next[i] = j;
        }
        return next;
    }
}
