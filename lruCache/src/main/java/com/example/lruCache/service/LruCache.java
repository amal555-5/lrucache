package com.example.lruCache.service;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache<K, V> {
    private final int capacity;
    private final Map<K, V> cache;

    public LruCache(int capacity) {
        this.capacity=capacity;
        this.cache=new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size()>capacity;
            }
        };
    }
    public synchronized V get(K key) {
        return cache.get(key);
    }
    public synchronized void put(K key, V value) {
        cache.put(key, value);
    }
    public synchronized Map<K, V> getAll() {
        return new LinkedHashMap<>(cache);
    }
}
