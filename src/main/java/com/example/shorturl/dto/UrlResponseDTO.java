package com.example.shorturl.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
public class UrlResponseDTO {
    private Long countClick;
    private String shortUrl;

    public UrlResponseDTO(Long count, String shortUrl) {
        this.countClick = count;
        this.shortUrl = shortUrl;
    }
}
