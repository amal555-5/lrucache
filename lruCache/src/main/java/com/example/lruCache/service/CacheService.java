package com.example.lruCache.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CacheService {
    private final LruCache<String, String> cache=new LruCache<>(5);

    public String get(String key) {
        return cache.get(key);
    }
    public void put(String key, String value) {
        cache.put(key, value);
    }
    public Map<String, String> getAll() {
        return cache.getAll();
    }
}
