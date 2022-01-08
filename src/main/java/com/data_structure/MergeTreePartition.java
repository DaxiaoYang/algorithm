package com.data_structure;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/16
 */
public class MergeTreePartition {

    private String[] usernames;

    private long[] times;

    private String[] actions;

    public static void main(String[] args) {
        int dataNum = 8192 * 3 + 100;
        MergeTreePartition mergeTreePartition = new MergeTreePartition();

    }

    private static class OperateLog {

        private String username;

        private long time;

        private String action;
    }
}
