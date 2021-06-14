package com.algorithm.string;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/28
 */
public class ReverseWordsInAString {

    public String reverseWords(String s) {
        // two pointere 两个指针i j 划定一个窗口 确定单词的范围
        StringBuilder sb = new StringBuilder();
        char[] c = s.toCharArray();
        int j = c.length - 1;
        // 从后往前遍历 忽略空格
        while (c[j] == ' ') {
            j--;
        }
        // 进入循环时 j指向遇到的单词的最后一个char
        while (j >= 0) {
            int i = j - 1;
            while (i >= 0 && Character.isLetterOrDigit(c[i])) {
                i--;
            }
            // 确定单词范围: [i + 1, j]
            for (int k = i + 1; k <= j; k++) {
                sb.append(c[k]);
            }
            // 跳过空格
            while (i >= 0 && c[i] == ' ') {
                i--;
            }
            // i < 0 说明是leading zeros
            if (i >= 0) {
                sb.append(' ');
            }
            // j重新指向遇到的单词的最后一个char
            j = i;
        }
        return sb.toString();
    }
}
