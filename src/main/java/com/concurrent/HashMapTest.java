package com.concurrent;

import java.util.HashMap;
import java.util.Map;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/22
 */
public class HashMapTest {

    public static void main(String[] args) {
        // 构造方法执行完成后 只是设置了数组长度和扩容阈值
        Map<Integer, String> map = new HashMap<>(4);
        // 在第一次执行put方法时才会真正创建数组(懒加载)
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        // 使用误区：capacity指的是底层数组的大小 扩容阈值是capacity * loadfactor(默认0.75)
        // 所以此时再put就会触发方法resize()
        map.put(4, "4");
        String s = map.get(1);
    }
}
