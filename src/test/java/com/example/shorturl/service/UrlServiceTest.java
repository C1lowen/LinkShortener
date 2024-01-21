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


@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {
//    @InjectMocks
//    private UrlService urlService;
//    @Mock
//    private UrlRepository urlRepository;
//
//    @Test
//    public void save(){
//        UrlDTO urlDTO = new UrlDTO("url", "shortUrl", 2);
//        ObjectURL objectURL = new ObjectURL("urlObj", "shortUrlObj", 1);
//        Mockito.when(urlRepository.findByUrl(urlDTO.getUrl())).thenReturn(objectURL);
//        String actual = urlService.save(urlDTO);
//        String expected = "shortUrlObj";
//        Assertions.assertThat(actual).isEqualTo(expected);
//    }

//    @Test
//    public void save_isNull(){
//        Mockito.when(urlRepository.findByUrl("url")).thenReturn(null);
//        Mockito.when(urlRepository.checkShortUrl(ArgumentMatchers.anyString())).thenReturn(Optional.empty());
//        String actual =urlService.save(new UrlDTO("url", "shortUrl", 2));
//        Mockito.verify(urlRepository).save(ArgumentMatchers.any(ObjectURL.class));
//        Assertions.assertThat(actual).isNotNull();
//    }
}
