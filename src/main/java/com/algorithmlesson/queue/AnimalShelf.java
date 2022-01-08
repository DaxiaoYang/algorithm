package com.algorithmlesson.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/19
 */
public class AnimalShelf {

    private Queue<int[]>[] queue = new Queue[2];

    private Queue<int[]> dogs = new LinkedList<>();

    private Queue<int[]> cats = new LinkedList<>();

    public AnimalShelf() {
        queue[0] = cats;
        queue[1] = dogs;
    }

    public void enqueue(int[] animal) {
        queue[animal[1]].offer(animal);
    }

    public int[] dequeueAny() {
        if (dogs.isEmpty() && cats.isEmpty()) {
            return new int[]{-1, -1};
        }
        if (dogs.isEmpty()) {
            return cats.poll();
        }
        if (cats.isEmpty()) {
            return dogs.poll();
        }
        return cats.peek()[0] < dogs.peek()[0] ? cats.poll() : dogs.poll();
    }

    public int[] dequeueDog() {
        if (dogs.isEmpty()) {
            return new int[]{-1, -1};
        }
        return dogs.poll();
    }

    public int[] dequeueCat() {
        if (cats.isEmpty()) {
            return new int[]{-1, -1};
        }
        return cats.poll();
    }
}
