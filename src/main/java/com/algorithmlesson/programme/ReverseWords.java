package com.algorithmlesson.programme;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/11/30
 */
public class ReverseWords {

    public static void main(String[] args) {
        System.out.println(reverseWords("a good   example"));
    }

    public static String reverseWords(String s) {
        int j = s.length() - 1;
        int i = s.length() - 1;
        StringBuilder res = new StringBuilder();
        // 从后往前定位每个单词的范围
        while (true) {
            // 定位单词末尾
            while (j >= 0 && s.charAt(j) == ' ') {
                j--;
            }
            i = j;
            // 定位单词开头
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            if (i == j) {
                // 此时i j 都为-1
                break;
            }
            // 每次找到的单词的范围[i + 1, j]
            for (int k = i + 1; k <= j; k++) {
                res.append(s.charAt(k));
            }
            j = i;
            // 拼接空格
            res.append(' ');
        }
        if (res.length() > 0) {
            // 去掉字符串末尾空格 setLength只是移动下标
            res.setLength(res.length() - 1);
        }
        return res.toString();
    }
}
