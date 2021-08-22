package com.algorithm.greedy;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/20
 */
public class Candy {

    /**
     * 评分比两边高的得的糖果必须比两边高
     * @param ratings 评分
     * @return
     */
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] candy = new int[len];
        candy[0] = 1;
        candy[len - 1] = 1;
        // 先从左向右遍历 局部：保证当前位置评分比左边高的 得的糖果为左边的+1 全局：所有位置评分比左边高的 得的糖果数多
        // 可以利用上一次比较的结果 类似于状态递推
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            } else {
                candy[i] = 1;
            }
        }
        // 从右向左遍历 局部：当前位置评分比右边高的 得的糖果比右边的高 且也要比左边的高 所以取Max
        // 可以利用上一次比较的结果
        int sum = candy[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candy[i] = Math.max(candy[i], candy[i + 1] + 1);
            }
            sum += candy[i];
        }
        // 两次遍历保证了比两边高的 得的糖比两边的多
        return sum;
    }
}
