package com.algorithmlesson.heap;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/19
 */
public class KClosest {

    public int[][] kClosest(int[][] points, int k) {
        Queue<Point2Distance> maxHeap = new PriorityQueue<>((Point2Distance p1, Point2Distance p2) -> (int)(p2.distance - p1.distance));
        for (int[] point : points) {
            maxHeap.offer(new Point2Distance(point));
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        int[][] res = new int[k][];
        int len = 0;
        for (Point2Distance point2Distance : maxHeap) {
            res[len++] = point2Distance.point;
        }
        return res;
    }

    private static class Point2Distance {

        int[] point;

        long distance;

        Point2Distance(int[] point) {
            this.point = point;
            distance = (long) point[0] * point[0] + (long) point[1] * point[1];
        }
    }
}
