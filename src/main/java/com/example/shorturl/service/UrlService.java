package com.example.shorturl.service;

import com.example.shorturl.dto.UrlDTO;
import com.example.shorturl.model.ObjectURL;
import com.example.shorturl.repository.UrlRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Service
public class UrlService {


    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Transactional
    public String save(UrlDTO urlDTO) {
        ObjectURL newUrlDTO = urlRepository.findByUrl(urlDTO.getUrl());
        if(newUrlDTO == null) {
            newUrlDTO = ObjectURL.of(urlDTO);
            if(!isWebsiteAvailable(urlDTO.getUrl())) return "Not Found";
            newUrlDTO.setShortUrl(generateShortUrl());
            urlRepository.save(newUrlDTO);
        }
        return newUrlDTO.getShortUrl();
    }
    @Transactional
    public String getUrl(String shortUrl){
        Optional<ObjectURL> objectURL = urlRepository.findByShortUrl(shortUrl);
        if(objectURL.isEmpty()) return null;

        var result= objectURL.get();
        urlRepository.saveCount(result.getCount() + 1, result.getUrl());

        return result.getUrl();
    }

    public Long getCount(String url) {
      return urlRepository.getCount(url);
    }
    private String generateShortUrl() {
        String shortUrl;
        do {
             shortUrl = RandomStringUtils.random(6, true, true);
        } while(urlRepository.checkShortUrl(shortUrl).isPresent());
        return shortUrl;
    }


    private boolean isWebsiteAvailable(String url) {

        try {
            URL siteURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            return connection.getResponseCode() >= 200 && connection.getResponseCode() < 300;
        } catch (Exception e) {
            return false;
        }
    }


}
