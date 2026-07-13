package com.example.ocp.locks;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class LockService {
    private final ConcurrentHashMap<String, Object> locks = new ConcurrentHashMap<>();

    public Object getLock(String key) {
        return locks.computeIfAbsent(key, s -> new Object());
    }

    public ConcurrentHashMap<String, Object> getLocks() {
        return locks;
    }

}

