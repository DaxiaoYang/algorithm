package com.algorithmlesson.binarysearch;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/31
 */
public class MinEatingSpeed {


    public static void main(String[] args) {
        int[] piles = {3,6,7,11};
        System.out.println(minEatingSpeed(piles, 8));
    }


    public static int minEatingSpeed(int[] piles, int h) {
        // 确定每小时吃的数量的上下界 极端情况下 h == piles.length 即要求每一堆香蕉必须一次吃完
        // 所以消费数量上下界就是数组中元素的上下界 1 <= piles[i] <= 10^9
        int lowerBound = 1, upperBound = 1000_000_000;
        // 问题可以转化为 在[1,10^9]区间内 找到第一个eatNum(因为想要慢慢吃) 使得其消费时间 <= h（在规定时间内吃完）
        int low = lowerBound, high = upperBound, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            // 命中： 规定时间内可以吃完香蕉
            if (getEatHours(piles, mid) <= h) {
                // 真命中: 吃的速度已经达到最慢 或 再吃慢一点就超时了
                if (mid == lowerBound || getEatHours(piles, mid - 1) > h) {
                    return mid;
                }
                // 伪命中: 还能吃得更慢
                high = mid - 1;
            } else {
                // 没命中 当前消费速度会超时
                low = mid + 1;
            }
        }
        return -1;
    }
    /**
     * @param piles 香蕉数组
     * @param eatNum 每次消费的香蕉数
     * @return 在当前消费速度下 需要多少个小时才能把香蕉吃完
     */
    private static int getEatHours(int[] piles, int eatNum) {
        int hours = 0;
        for (int pile : piles) {
            // (pile + eatNum - 1) / eatNum <=> ceil(pile / eatNum) 向上取整
            hours += (pile + eatNum - 1) / eatNum;
        }
        return hours;
    }
}
