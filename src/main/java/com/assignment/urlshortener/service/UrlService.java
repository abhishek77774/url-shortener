package com.assignment.urlshortener.service;

import com.assignment.urlshortener.dto.Request;
import com.assignment.urlshortener.model.Url;
import com.assignment.urlshortener.repository.UrlRepository;
import com.assignment.urlshortener.util.UrlConversionUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlService {
    private final UrlRepository urlRepository;

    /**
     * Converts an original URL into shortened URL and saves in database
     * @param request of type Request
     * @return shortened URL String
     */
    public String convertToShortUrl(Request request) {
        var url = new Url();
        url.setFullUrl(request.getUrlText());
        var entity = urlRepository.save(url);
        String shortUrl = UrlConversionUtil.encode(entity.getId());

        url.setId(entity.getId());
        url.setShortUrl(UrlConversionUtil.baseURL+shortUrl);
        urlRepository.save(url);

        log.info("URL {} is saved in DB", request.getUrlText());

        return shortUrl;
    }

    /**
     * Converts back a short URL into original URL
     * @param shortUrl String
     * @return original URL as String
     */
    public String getOriginalUrl(String shortUrl) {
        var id = UrlConversionUtil.decode(shortUrl);
        var entity = urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));
        return entity.getFullUrl();
    }
}
