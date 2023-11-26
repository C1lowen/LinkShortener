package com.example.shorturl.model;

import com.example.shorturl.dto.UrlDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.repository.Query;

@Entity(name = "urlservice")
@Data
public class ObjectURL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "long_url")
    private String url;
    @Column(name = "short_url")
    private String shortUrl;
    private long count;

    public ObjectURL() {
        this.count = 0L;
    }

    public ObjectURL(String url, String shortUrl, long count) {
        this.url = url;
        this.shortUrl = shortUrl;
        this.count = count;
    }

    public static ObjectURL of(UrlDTO urlDTO){
        return new ObjectURL(urlDTO.getUrl(), urlDTO.getShortUrl(), urlDTO.getCount());
    }

}
