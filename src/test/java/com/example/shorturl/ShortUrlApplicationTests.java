package com.example.shorturl;

import com.example.shorturl.testcontainersconfig.PostgresTestContainer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShortUrlApplicationTests implements PostgresTestContainer {

    @Test
    void contextLoads() {

    }

}
