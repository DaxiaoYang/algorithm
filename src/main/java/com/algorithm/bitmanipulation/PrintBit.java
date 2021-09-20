package com.algorithm.bitmanipulation;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/27
 */
public class PrintBit {

    public static void main(String[] args) {
//        printBit(-1);
        System.out.println(BitCount4(217));
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

    private static int BitCount4(int n) {
        printBit(n);
        n = (n &0x55555555) + ((n >>>1) &0x55555555) ;
        printBit(n);
        n = (n &0x33333333) + ((n >>>2) &0x33333333) ;
        printBit(n);
        n = (n &0x0f0f0f0f) + ((n >>>4) &0x0f0f0f0f) ;
        printBit(n);
        n = (n &0x00ff00ff) + ((n >>>8) &0x00ff00ff) ;
        printBit(n);
        n = (n &0x0000ffff) + ((n >>>16) &0x0000ffff) ;
        printBit(n);
        return n ;
    }
}
