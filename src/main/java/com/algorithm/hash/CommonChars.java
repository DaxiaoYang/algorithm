package com.algorithm.hash;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/11/10
 */
public class CommonChars {


    public List<String> commonChars(String[] words) {
        int len = 128;
        int[] res = new int[len];
        map(words[0], res);
        int[] temp = new int[len];
        // 取最小值
        for (int i = 1; i < words.length; i++) {
            Arrays.fill(temp, 0);
            map(words[i], temp);
            for (int j = 0; j < len; j++) {
                res[j] = Math.min(res[j], temp[j]);
            }
        }
        List<String> resStr = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            while (res[i] > 0) {
                resStr.add(String.valueOf((char)i));
                res[i]--;
            }
        }
        return resStr;
    }

    private void map(String word, int[] count) {
        for (int i = 0; i < word.length(); i++) {
            count[word.charAt(i)]++;
        }
    }
}
