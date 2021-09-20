package com.algorithm.array;

import java.util.Scanner;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/11
 */
public class TypeString {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        int z = in.nextInt();
        // 建立映射 ascii字符 -> 所在位置
        Point[] points = new Point[128];
        for (int i = 0; i < n; i++) {
            String line = in.nextLine();
            char[] chars = line.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                points[chars[j]] = new Point(i, j);
            }
        }
        String str = in.nextLine();
        // 不管多少都需要点击字符个数下
        int sum = str.length();
        // 初始位置
        Point currPoint = new Point(0, 0);
        // currDirection 值为 1 2 3 4 分别表示当前方向为左 上 右 下
        int currDirection = 0;
        for (char c : str.toCharArray()) {
            // 遍历计算当前位置到下一个位置 累积成本 并修改方向
            Point nextPoint = points[c];
            sum += countCost(currPoint, nextPoint, currDirection);
            currPoint = nextPoint;
            currDirection = getNextDirection(currPoint, nextPoint, currDirection);
        }
        System.out.println(sum);
    }

    private static int getNextDirection(Point currPoint, Point nextPoint, int currDirection) {
        return 0;
    }

    /**
     * 计算当前位置到下一个位置的成本：移动成本 + 转向成本
     */
    private static int countCost(Point currPoint, Point nextPoint, int currDirection) {
        if (currPoint.x == nextPoint.x && currPoint.y == nextPoint.y) {
            return 0;
        }
        int distance = Math.abs(currPoint.x - nextPoint.x) + Math.abs(currPoint.y - nextPoint.y);
        int cost = distance;
        int turnCost = 0;
        if (currDirection == 1) {
            if (nextPoint.x < currPoint.x && nextPoint.y != currPoint.y) {
                turnCost = 1;
            }
        } else if (currDirection == 2) {
            if (nextPoint.y > currPoint.y && nextPoint.x != currPoint.x) {
                turnCost = 1;
            }
        } else if (currDirection == 3) {
            if (nextPoint.x > currPoint.x && nextPoint.y != currPoint.y) {
                turnCost = 1;
            }
        } else if (currDirection == 4) {
            if (nextPoint.y < currPoint.y && nextPoint.x != currPoint.x) {
                turnCost = 1;
            }
        }
        cost += turnCost;
        return cost;
    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}
