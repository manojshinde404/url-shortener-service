package com.infracloud.assignment.url_shortener.repository;

import com.infracloud.assignment.url_shortener.model.UrlMapping;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UrlRepository {
    // Stores from shortCode -> UrlMapping object
    public static Map<String, UrlMapping> shortCodeToUrl = new ConcurrentHashMap<>();

    // Stores from originalUrl -> shortCode
    // Used to ensure same URL always returns the same short code
    public static Map<String, String> UrlToShortCode = new ConcurrentHashMap<>();
}
