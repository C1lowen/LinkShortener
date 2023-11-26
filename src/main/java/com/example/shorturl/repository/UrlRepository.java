package com.example.shorturl.repository;

import com.example.shorturl.dto.UrlDTO;
import com.example.shorturl.model.ObjectURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<ObjectURL, Long> {
    ObjectURL findByUrl(String url);


    @Query(value = "select * from urlservice where short_url = :shortUrl",nativeQuery = true)
    Optional<ObjectURL> findByShortUrl(@Param("shortUrl") String shortUrl);

    @Query(value = "update urlservice set count = :count where long_url = :longUrl",nativeQuery = true)
    @Modifying
    void saveCount(@Param("count") Long count, @Param("longUrl") String url);

    @Query(value = "select * from urlservice where short_url = :shortUrl",nativeQuery = true)
    Optional<ObjectURL> checkShortUrl(@Param("shortUrl") String shortUrl);

    @Query(value = "select count from urlservice where short_url = :url",nativeQuery = true)
    Long getCount(@Param("url") String url);
}
