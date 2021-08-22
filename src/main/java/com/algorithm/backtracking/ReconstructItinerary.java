package com.algorithm.backtracking;

import java.util.*;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/6
 */
public class ReconstructItinerary {


    public static void main(String[] args) {
        List<List<String>> tickets = Arrays.asList(Arrays.asList("JFK","KUL"),Arrays.asList("JFK","NRT"),Arrays.asList("NRT","JFK"));
        List<String> itinerary = findItinerary(tickets);
        System.out.println(itinerary);
    }

    public static List<String> findItinerary(List<List<String>> tickets) {
        Deque<String> route = new LinkedList<>();
        // 出发 -> (到达 -> 该航班使用次数)
        Map<String, Map<String, Integer>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            Map<String, Integer> temp;
            if (map.containsKey(ticket.get(0))) {
                // 已存在该出发航班 获取目的地集合
                temp = map.get(ticket.get(0));
                // 计数
                temp.put(ticket.get(1), temp.getOrDefault(ticket.get(1), 0) + 1);
            } else {
                // 自然排序 按字符
                temp = new TreeMap<>();
                // 第一次初始化
                temp.put(ticket.get(1), 1);
            }
            map.put(ticket.get(0), temp);
        }
        route.add("JFK");
        backtrack(route, map, tickets);
        return new ArrayList<>(route);
    }

    private static boolean backtrack(Deque<String> route, Map<String, Map<String, Integer>> map, List<List<String>> tickets) {
        if (route.size() == tickets.size() + 1) {
            return true;
        }
        String lastVisit = route.peekLast();
        if (!map.containsKey(lastVisit)) {
            return false;
        }
        for (Map.Entry<String, Integer> dests : map.get(lastVisit).entrySet()) {
            int count = dests.getValue();
            if (count > 0) {
                route.offer(dests.getKey());
                dests.setValue(count - 1);
                if (backtrack(route, map, tickets)) {
                    return true;
                }
                dests.setValue(count);
                // 不要用poll 那是丢队头
                route.pollLast();
            }
        }
        return false;
    }
}
