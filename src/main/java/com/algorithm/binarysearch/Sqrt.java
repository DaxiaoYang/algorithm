package com.algorithm.binarysearch;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/20
 */
public class Sqrt {

    public static void main(String[] args) {
        System.out.println(mySqrt2(Integer.MAX_VALUE));
    }

    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        // low得取1 要不然[0,high]这个范围mid会取到0 发生除0异常
        int low = 1, high = x, mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (mid > x / mid) {
                high = mid - 1;
            } else {
                if (mid + 1 > x / (mid + 1)) {
                    return mid;
                }
                low = mid + 1;
            }
        }
        return -1;
    }

    public static int mySqrt2(int x) {
        if (x == 0) {
            return 0;
        }
        // 牛顿法 把问题看成已知 n 求 x 使得 x^2 = n -> 求 f(x) = x^2 - n 的根
        // 画切线 找切线与x轴交掉 做垂线 再画切线找切线与x轴交点 一点点逼近根
        long next = x;
        while (next > x / next) {
            // 用long是为了避免 用int时 x = Integer.MAX_VALUE时 next + x / next 溢出的情况
            next = (next + x / next) / 2;
        }
        return (int)next;
    }
}
