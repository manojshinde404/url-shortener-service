package com.infracloud.assignment.url_shortener.controller;

import com.infracloud.assignment.url_shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public Map<String, String> shorten(@RequestBody Map<String, String> request) {

        String shortCode = urlService.shortenUrl(request.get("url"));

        return Map.of("shortUrl", "http://localhost:8080/api/" + shortCode);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code){

        String url = urlService.getOriginalUrl(code);

        return ResponseEntity
                .status(302)
                .location(URI.create(url))
                .build();
    }
}
