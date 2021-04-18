package com.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddress {

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525511135"));
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) {
            return res;
        }
        dfs(0, 0, s, new StringBuilder(), res);
        return res;
    }

    private static void dfs(int index, int num, String ip, StringBuilder temp, List<String> res) {
        // 超过4个的分支 直接切了
        if (num > 4) {
            return;
        }
        // 符合条件
        if (num == 4 && index == ip.length()) {
            // 去掉末尾的.
            res.add(temp.substring(0, temp.length() - 1));
            return;
        }
        // 每次最多选3个字符
        for (int i = 1; i <= 3; i++) {
            // 如果选的范围超过了
            if (index + i > ip.length()) {
                break;
            }
            // 截取当前选择的字符串
            String part = ip.substring(index, index + i);
            // 如果是以0开头且位数大于1 或者是 3位数且不在范围内的 直接跳过
            if (part.charAt(0) == '0' && part.length() > 1 || i == 3 && Integer.parseInt(part) > 255) {
                continue;
            }
            // 选择当前字符串
            // 保存当前字符串长度
            int len = temp.length();
            temp.append(part).append(".");
            dfs(index + i, num + 1, ip, temp, res);
            // 删除StringBuilder字符不要用delete 或者 deteleCharAt 会涉及到复制字符数组的操作 直接用setLength移动指针即可
            temp.setLength(len);
//            temp.delete(temp.length() - i - 1, temp.length());
        }
    }
}
