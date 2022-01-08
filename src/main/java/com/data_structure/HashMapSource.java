package com.data_structure;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/23
 */
public class HashMapSource {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("1", UUID.randomUUID().toString());
        map.put("2", UUID.randomUUID().toString());
        map.put("3", UUID.randomUUID().toString());
        System.out.println(map.get("1"));
    }
}
