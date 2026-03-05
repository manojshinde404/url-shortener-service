package com.infracloud.assignment.url_shortener.model;

public class UrlMapping {
    private final String originalUrl;
    private final String shortCode;
    private final String domain;

    public UrlMapping(String originalUrl, String shortCode, String domain) {
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.domain = domain;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getDomain() {
        return domain;
    }
}
