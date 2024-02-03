package com.example.shorturl.service;

import com.example.shorturl.dto.UrlDTO;
import com.example.shorturl.model.ObjectURL;
import com.example.shorturl.repository.UrlRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {
    @InjectMocks
    private UrlService urlService;
    @Mock
    private UrlRepository urlRepository;

    @Test
    public void save(){
        UrlDTO urlDTO = new UrlDTO("url", "shortUrl", 2);
        ObjectURL objectURL = new ObjectURL("urlObj", "shortUrlObj", 1);
        when(urlRepository.findByUrl(urlDTO.getUrl())).thenReturn(objectURL);

        String actual = urlService.save(urlDTO);
        String expected = "shortUrlObj";

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void save_isNull(){
        UrlDTO urlDTO = new UrlDTO("https://www.google.com/", "shortUrl", 2);
        when(urlRepository.findByUrl(urlDTO.getUrl())).thenReturn(null);
        when(urlRepository.checkShortUrl(ArgumentMatchers.anyString())).thenReturn(Optional.empty());

        String actual = urlService.save(urlDTO);

        verify(urlRepository).save(ArgumentMatchers.any(ObjectURL.class));
        Assertions.assertThat(actual).isNotNull();
    }

    @Test
    public void getUrl_ReturnedUrl(){
        ObjectURL objectURL = new ObjectURL("urlObj", "shortUrlObj", 1L);
        Optional<ObjectURL> optionalObjectURL = Optional.of(objectURL);

        when(urlRepository.findByShortUrl("shortUrlObj")).thenReturn(optionalObjectURL);
        String expected = "urlObj";
        String actual = urlService.getUrl("shortUrlObj");

        Assertions.assertThat(actual).isEqualTo(expected);
        verify(urlRepository).saveCount(2L, "urlObj");
    }

    @Test
    public void getUrl_shouldNull(){
        when(urlRepository.findByShortUrl("lol")).thenReturn(Optional.empty());

        String actual = urlService.getUrl("lol");

        Assertions.assertThat(actual).isNull();
    }

   @Test
    public void getCount_checkReturned(){
        when(urlRepository.getCount("urlFull")).thenReturn(1L);
        Long actual = urlService.getCount("urlFull");

        Assertions.assertThat(actual).isEqualTo(1L);
        Assertions.assertThat(actual).isNotNull();
        verify(urlRepository).getCount("urlFull");
   }
}
