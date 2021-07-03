package com.algorithm.bitmanipulation;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/27
 */
public class PrintBit {

    public static void main(String[] args) {
        printBit(-1);
    }

    public static void printBit(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            int lastBit = num & 1;
            num >>>= 1;
            sb.insert(0, lastBit);
        }
        System.out.println(sb);
    }
}
