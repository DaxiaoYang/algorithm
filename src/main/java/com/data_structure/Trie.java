package com.data_structure;

/**
 * 字典树：本质上是避免重复存储相同的字符串前缀 空间复杂度会收到字符集大小的影响
 */
public class Trie {

    private TrieNode root = new TrieNode('/');

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("hello");
        trie.insert("her");
        trie.insert("his");
        trie.insert("hi");
        System.out.println(trie.find("his"));
        System.out.println(trie.find("he"));
    }

    public void insert(String text) {
        TrieNode curr = root;
        char[] chars = text.toCharArray();
        // 遍历字符串 将字符插入到各个位置上
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode(chars[i]);
            }
            curr = curr.children[index];
        }
        curr.isEndChar = true;
    }

    public boolean find(String text) {
        TrieNode curr = root;
        char[] chars = text.toCharArray();
        for (char aChar : chars) {
            int index = aChar - 'a';
            if (curr.children[index] == null) {
                return false;
            }
            curr = curr.children[index];
        }
        return curr.isEndChar;
    }

    static class TrieNode {
        char data;
        boolean isEndChar;
        // 这里假设只存储小写字母
        TrieNode[] children = new TrieNode[26];

        public TrieNode(char data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "TrieNode{" +
                    "data=" + data +
                    ", isEndChar=" + isEndChar +
                    '}';
        }
    }
}
