package com.example.lruCache.controller;

import com.example.lruCache.service.LruCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class CacheController {
    @Autowired
    private LruCache cache;

    @GetMapping("/get")
    public String getValue(@RequestParam String key) {
        String value = cache.get(key);
        return value == null ? "Key not found" : value;
    }
    @PostMapping("/put")
    public String putValue(@RequestParam String key, @RequestParam String value) {
        cache.put(key, value);
        return "Stored (" + key + " = " + value + ")";
    }
    @GetMapping("/all")
    public Object getAll() {
        return cache.getAll();
    }
}
