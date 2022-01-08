package com.data_structure;

import java.util.BitSet;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/27
 * 位图
 */
public class BitMap {

    private byte[] bytes;

    private int bitNum;

    public BitMap(int bitNum) {
        this.bytes = new byte[bitNum / 8 + 1];
        this.bitNum = bitNum;
    }

    public boolean set(int num) {
        if (num > bitNum) {
            return false;
        }
        bytes[num >>> 3] |= 1 << (num % 8);
        return true;
    }

    public boolean exist(int num) {
        if (num > bitNum) {
            return false;
        }
        return (bytes[num >>> 3] & (1 << (num % 8))) != 0;
    }

    public static void main(String[] args) {
//        BitSet bitSet = new BitSet();
//        BitMap bitMap = new BitMap(15);
//        bitMap.set(1);
//        bitMap.set(4);
//        bitMap.set(13);
//        System.out.println(bitMap.exist(1));
//        System.out.println(bitMap.exist(4));
//        System.out.println(bitMap.exist(13));
//        System.out.println(bitMap.exist(14));

        int num = 2;
        System.out.println(1 + (num >> 1));
    }
}
