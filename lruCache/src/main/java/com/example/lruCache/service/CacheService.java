package com.example.lruCache.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class CacheService {
    private final LruCache<String, String> cache=new LruCache<>(5);

    public Map<String, Object> put(String key, String value) {
        cache.put(key, value);
        Map.Entry<String, String> evicted = cache.getLastEvictedEntry();

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", "Success");
        response.put("message", "Entry added successfully");
        response.put("stored", Map.of("key", key, "value", value));
        if (evicted!=null) {
            response.put("evicted", Map.of("key", evicted.getKey(), "value", evicted.getValue()));
        }
        return response;
    }
    public Map<String, Object> get(String key) {
        String value = cache.get(key);
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now().toString());
        if (value!=null) {
            response.put("status", "Success");
            response.put("key", key);
            response.put("value", value);
        } else {
            response.put("status", "Not found");
            response.put("message", "Key not found in cache");
        }
        return response;
    }
    public Map<String, Object> getAll() {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", "Success");
        response.put("cacheEntries", cache.getAll());
        return response;
    }
}
