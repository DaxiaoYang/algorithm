package com.algorithmlesson.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/22
 */
public class CombinationOfPhoneNumber {

    private static final char[][] map;

    static {
        map = new char[10][];
        map[2] = new char[]{'a', 'b', 'c'};
        map[3] = new char[]{'d', 'e', 'f'};
        map[4] = new char[]{'g', 'h', 'i'};
        map[5] = new char[]{'j', 'k', 'l'};
        map[6] = new char[]{'m', 'n', 'o'};
        map[7] = new char[]{'p', 'q', 'r', 's'};
        map[8] = new char[]{'t', 'u', 'v'};
        map[9] = new char[]{'w', 'x', 'y', 'z'};
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }

    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        backtrack(0, digits, new StringBuilder(), res);
        return res;
    }

    private static void backtrack(int start, String digits, StringBuilder path, List<String> res) {
        if (start == digits.length()) {
            res.add(path.toString());
            return;
        }
        int len = path.length();
        int digit = digits.charAt(start) - '0';
        for (int j = 0; j < map[digit].length; j++) {
            path.append(map[digit][j]);
            backtrack(start + 1, digits, path, res);
            path.setLength(len);
        }
    }
}
