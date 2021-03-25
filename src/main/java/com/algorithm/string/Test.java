package com.algorithm.string;

import java.util.Scanner;

public class Test {

    /**
     * 企业发放的销售奖金根据利润提成。
     * 利润(I)低于或等于10 万元时,奖金可提5%；利润高于10 万元,低于等于20 万元时,低于10 万元的部分按5%提成,
     * 高于10 万元的部分,可可提成7%；
     * 20 万以上时,高于20 万元的部分,可提成10%；从键盘输入当月利润,求应发放奖金总数？
     */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double cache1 = 100_000 * 0.05;
        double cache2 = 100_000 * 0.12;
        int input = 0;
        while ((input = in.nextInt()) != 0) {
            if (input <= 100_000) {
                System.out.println(input * 0.05);
            } else if (input <= 200_000) {
                System.out.println(cache1 + (input - 100_000) * 0.07);
            } else {
                System.out.println(cache2 + (input - 200_000) * 0.1);
            }
        }
    }
}
