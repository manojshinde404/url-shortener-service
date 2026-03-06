package com.infracloud.assignment.url_shortener.controller;

import com.infracloud.assignment.url_shortener.model.UrlMapping;
import com.infracloud.assignment.url_shortener.repository.UrlRepository;
import com.infracloud.assignment.url_shortener.service.impl.UrlServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UrlControllerTest {

    private final UrlServiceImpl urlService = new UrlServiceImpl();

    @Test
    void testShortenUrl() {

        String url = "https://www.youtube.com";

        String shortCode = urlService.shortenUrl(url);

        assertNotNull(shortCode);
    }

    @Test
    void testSameUrlReturnsSameCode() {

        String url = "https://www.google.com";

        String code1 = urlService.shortenUrl(url);
        String code2 = urlService.shortenUrl(url);

        assertEquals(code1, code2);
    }
    @Test
    void testMetricsApi(){

        // Clear repository before test
        UrlRepository.shortCodeToUrl.clear();

        // sample data
        UrlRepository.shortCodeToUrl.put("a1",
                new UrlMapping("https://youtube.com/video1","a1","youtube.com"));

        UrlRepository.shortCodeToUrl.put("a2",
                new UrlMapping("https://youtube.com/video2","a2","youtube.com"));

        UrlRepository.shortCodeToUrl.put("a3",
                new UrlMapping("https://youtube.com/video3","a3","youtube.com"));

        UrlRepository.shortCodeToUrl.put("a4",
                new UrlMapping("https://youtube.com/video4","a4","youtube.com"));

        UrlRepository.shortCodeToUrl.put("b1",
                new UrlMapping("https://udemy.com/course/java","b1","udemy.com"));

        UrlRepository.shortCodeToUrl.put("b2",
                new UrlMapping("https://udemy.com/course/docker","b2","udemy.com"));

        UrlRepository.shortCodeToUrl.put("b3",
                new UrlMapping("https://udemy.com/course/spring","b3","udemy.com"));

        UrlRepository.shortCodeToUrl.put("b4",
                new UrlMapping("https://udemy.com/course/kubernetes","b4","udemy.com"));

        UrlRepository.shortCodeToUrl.put("b5",
                new UrlMapping("https://udemy.com/course/microservices","b5","udemy.com"));

        UrlRepository.shortCodeToUrl.put("b6",
                new UrlMapping("https://udemy.com/course/react","b6","udemy.com"));

        UrlRepository.shortCodeToUrl.put("c1",
                new UrlMapping("https://en.wikipedia.org/wiki/Java","c1","wikipedia.org"));

        UrlRepository.shortCodeToUrl.put("c2",
                new UrlMapping("https://en.wikipedia.org/wiki/Spring_Framework","c2","wikipedia.org"));

        UrlController controller = new UrlController();

        Map<String, Long> result = controller.getMetrics();

        assertTrue(result.containsKey("youtube.com"));
        assertEquals(4L, result.get("youtube.com"));
    }
}
