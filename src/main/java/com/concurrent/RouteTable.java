package com.concurrent;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @ description: 
 * @ author: daxiao
 * @ date: 2021/10/14 
 */
public class RouteTable {

    private Map<String, CopyOnWriteArraySet<Route>> map = new ConcurrentHashMap<>();

    private volatile boolean changed;

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public void startCheckTask() {
        executorService.schedule(this::autoSave, 5, TimeUnit.SECONDS);
    }

    public Set<Route> getRoute(String iface) {
        return map.get(iface);
    }

    public void autoSave() {
        if (!changed) {
            return;
        }
        changed = false;
        // save to local disk
    }

    public void removeRoute(Route route) {
        CopyOnWriteArraySet<Route> routes = map.get(route.getIface());
        if (routes != null) {
            routes.remove(route);
        }
        changed = true;
    }

    public void addRoute(Route route) {
        map.putIfAbsent(route.getIface(), new CopyOnWriteArraySet<>());
        map.get(route.getIface()).add(route);
        changed = true;
    }

    private static class Route {

        private final String ip;
        private final int port;
        private final String iface;

        public Route(String ip, int port, String iface) {
            this.ip = ip;
            this.port = port;
            this.iface = iface;
        }

        public String getIp() {
            return ip;
        }

        public int getPort() {
            return port;
        }

        public String getIface() {
            return iface;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Route route = (Route) o;
            return port == route.port && Objects.equals(ip, route.ip) && Objects.equals(iface, route.iface);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ip, port, iface);
        }
    }
}
