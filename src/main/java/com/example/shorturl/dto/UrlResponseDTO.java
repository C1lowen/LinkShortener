package com.example.shorturl.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
public class UrlResponseDTO {
    private Long countClick;
    private String originalUrl;
    private String shortUrl;

    public UrlResponseDTO(Long countClick, String originalUrl, String shortUrl) {
        this.countClick = countClick;
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
    }
}
