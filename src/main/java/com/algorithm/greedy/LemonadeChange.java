package com.algorithm.greedy;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/22
 */
public class LemonadeChange {

    public static void main(String[] args) {
        int[] bills = {5,5,10,20,5,5,5,5,5,5,5,5,5,10,5,5,20,5,20,5};
        System.out.println(lemonadeChange(bills));
    }

    public static boolean lemonadeChange(int[] bills) {
        // 只需记录5 10的情况因为 找零就靠他们
        int five = 0;
        int ten = 0;
        for (int bill : bills) {
            // 5块的情况 直接收下
            if (bill == 5) {
                five++;
            }
            // 10块的情况 需要至少有个一个5块
            if (bill == 10) {
                if (five <= 0) {
                    return false;
                }
                five--;
                ten++;
            }
            // 20块的情况 10 + 5 和 5 * 3都能满足 但是优先使用10块
            // 因为10块只能给20找零 但是5能给10和20找零 更有价值
            // 局部最优：优先使用掉比较没有价值的（不通用的）10块
            if (bill == 20) {
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
