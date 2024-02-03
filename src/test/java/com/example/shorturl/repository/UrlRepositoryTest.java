package com.example.shorturl.repository;

import com.example.shorturl.model.ObjectURL;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UrlRepositoryTest {
    @Autowired
    private UrlRepository objectURLRepository;

    private ObjectURL objectURL;

    @BeforeEach
    public void setupObjectURL(){
        objectURL = new ObjectURL();
        objectURL.setUrl("testUrl");
        objectURL.setCount(1);
        objectURL.setShortUrl("testShortUrl");
    }

    @Test
    public void testFindByUrl() {
        ObjectURL obj = objectURLRepository.save(objectURL);

        ObjectURL actual = objectURLRepository.findByUrl("testUrl");

        assertEquals(actual, obj);
        assertThat(actual).isNotNull();
        assertThat(actual.getUrl()).isEqualTo("testUrl");
        assertThat(actual.getShortUrl()).isEqualTo("testShortUrl");
        assertThat(actual.getCount()).isEqualTo(1);
    }

    @Test
    public void testFindByShortUrl() {
        objectURLRepository.save(objectURL);

        Optional<ObjectURL> foundObjectURL = objectURLRepository.findByShortUrl("testShortUrl");

        assertThat(foundObjectURL).isPresent();
        assertThat(foundObjectURL.orElse(null)).isNotNull();
        assertThat(foundObjectURL.orElse(null).getShortUrl()).isEqualTo("testShortUrl");
        assertThat(foundObjectURL.orElse(null).getUrl()).isEqualTo("testUrl");
    }

    @Test
    public void testSaveCount() {
        objectURLRepository.save(objectURL);
        Long expected = 2L;

        objectURLRepository.saveCount(expected, "testUrl");
        Long countActual = objectURLRepository.getCount("testShortUrl");

        assertNotNull(countActual);
        assertEquals(expected, countActual);
    }

    @Test
    public void testCheckShortUrl() {
        objectURLRepository.save(objectURL);

        Optional<ObjectURL> actual = objectURLRepository.checkShortUrl("testShortUrl");

        assertThat(actual).isPresent();
        assertThat(actual.orElse(null)).isNotNull();
        assertThat(actual.orElse(null).getShortUrl()).isEqualTo("testShortUrl");
        assertThat(actual.orElse(null).getUrl()).isEqualTo("testUrl");
    }

    @Test
    public void testGetCount() {
        objectURLRepository.save(objectURL);

        Long actual = objectURLRepository.getCount("testShortUrl");

        assertThat(actual).isEqualTo(1L);
    }
}
