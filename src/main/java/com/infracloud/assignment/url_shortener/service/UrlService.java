package com.infracloud.assignment.url_shortener.service;

public interface UrlService {
    String shortenUrl(String ur);
    String getOriginalUrl(String code);
}
