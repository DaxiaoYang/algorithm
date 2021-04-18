package com.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * I'm sure somewhere can be simplified so it'd be nice if anyone can let me know. The pattern was that:
 *
 * say n = 4, you have {1, 2, 3, 4}
 *
 * If you were to list out all the permutations you have
 *
 * 1 + (permutations of 2, 3, 4)
 *
 * 2 + (permutations of 1, 3, 4)
 *
 * 3 + (permutations of 1, 2, 4)
 *
 * 4 + (permutations of 1, 2, 3)
 *
 *
 * We know how to calculate the number of permutations of n numbers... n! So each of those with permutations of 3 numbers means there are 6 possible permutations. Meaning there would be a total of 24 permutations in this particular one. So if you were to look for the (k = 14) 14th permutation, it would be in the
 *
 * 3 + (permutations of 1, 2, 4) subset.
 *
 * To programmatically get that, you take k = 13 (subtract 1 because of things always starting at 0) and divide that by the 6 we got from the factorial, which would give you the index of the number you want. In the array {1, 2, 3, 4}, k/(n-1)! = 13/(4-1)! = 13/3! = 13/6 = 2. The array {1, 2, 3, 4} has a value of 3 at index 2. So the first number is a 3.
 *
 * Then the problem repeats with less numbers.
 *
 * The permutations of {1, 2, 4} would be:
 *
 * 1 + (permutations of 2, 4)
 *
 * 2 + (permutations of 1, 4)
 *
 * 4 + (permutations of 1, 2)
 *
 * But our k is no longer the 14th, because in the previous step, we've already eliminated the 12 4-number permutations starting with 1 and 2. So you subtract 12 from k.. which gives you 1. Programmatically that would be...
 *
 * k = k - (index from previous) * (n-1)! = k - 2*(n-1)! = 13 - 2*(3)! = 1
 *
 * In this second step, permutations of 2 numbers has only 2 possibilities, meaning each of the three permutations listed above a has two possibilities, giving a total of 6. We're looking for the first one, so that would be in the 1 + (permutations of 2, 4) subset.
 *
 * Meaning: index to get number from is k / (n - 2)! = 1 / (4-2)! = 1 / 2! = 0.. from {1, 2, 4}, index 0 is 1
 *
 *
 * so the numbers we have so far is 3, 1... and then repeating without explanations.
 *
 *
 * {2, 4}
 *
 * k = k - (index from pervious) * (n-2)! = k - 0 * (n - 2)! = 1 - 0 = 1;
 *
 * third number's index = k / (n - 3)! = 1 / (4-3)! = 1/ 1! = 1... from {2, 4}, index 1 has 4
 *
 * Third number is 4
 *
 *
 * {2}
 *
 * k = k - (index from pervious) * (n - 3)! = k - 1 * (4 - 3)! = 1 - 1 = 0;
 *
 * third number's index = k / (n - 4)! = 0 / (4-4)! = 0/ 1 = 0... from {2}, index 0 has 2
 *
 * Fourth number is 2
 *
 *
 * Giving us 3142. If you manually list out the permutations using DFS method, it would be 3142. Done! It really was all about pattern finding.
 */
public class PermutationSequence {

    public static void main(String[] args) {
        System.out.println(getPermutation(3, 3));
    }

    /**
     * 将问题分解为子问题题
     * 如求解 (1,2,3)的全排列可以分解为 求
     * 1 + (2,3) 可能的排列数为2!
     * 2 + (1,3)
     * 3 + (1,2)
     * 这三个子问题（抽象为3个数组） 一个共有3!个组合 即 3 * 2！
     * 如果要求的全排列序列数为3 找第三个数 那么肯定落在第二个数组中
     * 数组索引计算方式: (先将k-1 方面计算数组下标) index = k / subFactorial -> 2 / 2 = 1
     * 所以第一个数为2
     * 继续在(1,3)的全排列中 找到某个次序的数 次序k迭代： k = k - index * subFactorial  2 - 1 * 2 = 0
     * 1 + (3)
     * 3 + (1)
     * index = 0 / 1 = 0
     * 选择 1
     * 之后 k = 0 - 0 * 1 = 0
     * 求解
     * (3)
     * index = 0 / 1 = 0
     * 选择3
     * 所以结果为213
     */
    public static String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>(n);
        // 计算阶乘 因为 1234..n的数的全排列组合数 为n!
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            nums.add(i);
            factorial *= i;
        }
        // nums: {1,2,3}
        StringBuilder res = new StringBuilder();
        // k减去1 用于计算数组下标
        k--;
        for (int i = 0; i < n; i++) {
            factorial /= (n - i);
            int index = k / factorial;
            res.append(nums.get(index));
            nums.remove(index);
            k -= index * factorial;
        }
        return res.toString();
    }
}
