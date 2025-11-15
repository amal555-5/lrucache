package com.example.lruCache.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import java.io.File;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class LruCache {
    private static final String CACHE_FILE = "cache.json";
    private static final int CAPACITY = 5;

    private final ObjectMapper mapper = new ObjectMapper();

    private LinkedHashMap<String, String> cache =
            new LinkedHashMap<>(CAPACITY, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                    return size() > CAPACITY;
                }
            };
    @PostConstruct
    public void loadCacheFromDisk() {
        try {
            File file = new File(CACHE_FILE);
            if (file.exists()) {
                Map<String, String> data =
                        mapper.readValue(file, new TypeReference<Map<String, String>>() {});
                cache.putAll(data);
                System.out.println("Loaded cache from disk: " + cache);
            }
        } catch (Exception e) {
            System.err.println("Failed to load cache: " + e.getMessage());
        }
    }
    @PreDestroy
    public void saveCacheToDisk() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(CACHE_FILE), cache);
            System.out.println("Saved cache to disk");
        } catch (Exception e) {
            System.err.println("Failed to save cache: " + e.getMessage());
        }
    }
    public synchronized void put(String key, String value) {
        cache.put(key, value);
    }
    public synchronized String get(String key) {
        return cache.getOrDefault(key, null);
    }
    public synchronized Map<String, String> getAll() {
        return cache;
    }
}
