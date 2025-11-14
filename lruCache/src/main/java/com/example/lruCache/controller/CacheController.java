package com.example.lruCache.controller;

import com.example.lruCache.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cache")
public class CacheController {
    @Autowired
    private CacheService cacheService;

    @PostMapping("/put")
    public ResponseEntity<Map<String, Object>> put(@RequestParam String key, @RequestParam String value) {
        return ResponseEntity.ok(cacheService.put(key, value));
    }
    @GetMapping("/get/{key}")
    public ResponseEntity<Map<String, Object>> get(@PathVariable String key) {
        return ResponseEntity.ok(cacheService.get(key));
    }
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll() {
        return ResponseEntity.ok(cacheService.getAll());
    }
}
