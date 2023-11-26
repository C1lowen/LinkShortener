package com.example.shorturl.dto;

import com.example.shorturl.model.ObjectURL;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UrlDTO {
    private String url;
    private String shortUrl;
    private long count;


    public UrlDTO(String url, String shortUrl, long count) {
        this.url = url;
        this.shortUrl = shortUrl;
        this.count = count;
    }
}
