package com.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LetterCombinationsofaPhoneNumber {

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }

    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) {
            return res;
        }
        char[][] map = getMap();
        dfs(0, digits.toCharArray(), map, res, new StringBuilder());
        return res;
    }

    private static void dfs(int index, char[] digits, char[][] map, List<String> res, StringBuilder temp) {
        if (temp.length() == digits.length) {
            res.add(temp.toString());
            return;
        }
        // 当前阶段可以选择的字符
        char[] chars = map[digits[index] - '0'];
        for (int i = 0; i < chars.length; i++) {
            temp.append(chars[i]);
            // 下一个阶段可以选择的字符
            dfs(index + 1, digits, map, res, temp);
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    /**
     * 返回一个map 数组下标中的元素对应的就是数组下标对应的字符集
     */
    private static char[][] getMap() {
        char[][] map = new char[10][];
        map[0] = new char[0];
        map[1] = new char[0];
        map[2] = new char[]{'a', 'b', 'c'};
        map[3] = new char[]{'d', 'e', 'f'};
        map[4] = new char[]{'g', 'h', 'i'};
        map[5] = new char[]{'j', 'k', 'l'};
        map[6] = new char[]{'m', 'n', 'o'};
        map[7] = new char[]{'p', 'q', 'r', 's'};
        map[8] = new char[]{'t', 'u', 'v'};
        map[9] = new char[]{'w', 'x', 'y', 'z'};
        return map;
    }
}
