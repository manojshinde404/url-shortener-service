package com.infracloud.assignment.url_shortener.service.impl;

import com.infracloud.assignment.url_shortener.model.UrlMapping;
import com.infracloud.assignment.url_shortener.repository.UrlRepository;
import com.infracloud.assignment.url_shortener.service.UrlService;
import com.infracloud.assignment.url_shortener.util.Base62Encoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UrlServiceImpl implements UrlService {

    private AtomicLong counter = new AtomicLong(1000);

    @Override
    public String shortenUrl(String url) {
        if(UrlRepository.UrlToShortCode.containsKey(url)) {
            return UrlRepository.UrlToShortCode.get(url);
        }

        long id = counter.incrementAndGet();
        String shortenCode = Base62Encoder.encode(id);

        String domain = extractDomain(url);

        UrlMapping mapping = new UrlMapping(url, shortenCode, domain);

        UrlRepository.shortCodeToUrl.put(shortenCode, mapping);
        UrlRepository.UrlToShortCode.put(url, shortenCode);

        return shortenCode;

    }

    private String extractDomain(String url) {

        try {
            URI uri = new URI(url);
            return uri.getHost().replace("www.", "");
        } catch (Exception e) {
            return "unknown";
        }
    }

    @Override
    public String getOriginalUrl(String code) {
        UrlMapping urlMapping = UrlRepository.shortCodeToUrl.get(code);
        if(urlMapping == null){
            throw new RuntimeException("Short Url not found");
        }

        return urlMapping.getOriginalUrl();
    }
}
