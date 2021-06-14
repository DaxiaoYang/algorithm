package com.algorithm.hash;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/24
 */
public class RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];
        for (char c : magazine.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c: ransomNote.toCharArray()) {
            if (--count[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
