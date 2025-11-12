package com.example.lruCache.controller;

import com.example.lruCache.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cache")
public class CacheController {
    @Autowired
    private CacheService cacheService;

    @PostMapping("/put")
    public String put(@RequestParam String key, @RequestParam String value) {
        cacheService.put(key, value);
        return "Stored: (" + key + ", " + value + ")";
    }

    @GetMapping("/get/{key}")
    public String get(@PathVariable String key) {
        String value = cacheService.get(key);
        return value != null ? "Value: " + value : "Key not found.";
    }

    @GetMapping("/all")
    public Map<String, String> getAll() {
        return cacheService.getAll();
    }
}
