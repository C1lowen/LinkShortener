package com.example.shorturl.testcontainersconfig;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public interface PostgresTestContainer {
    @Container
    PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15");



    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        final String jdbcUrl = postgreSQLContainer.getJdbcUrl().replace("tc:", "");
        System.out.println(jdbcUrl);

        registry.add("spring.datasource.url", () -> jdbcUrl);
        registry.add("spring.datasource.username=", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password=", postgreSQLContainer::getPassword);
        registry.add("spring.flyway.url=", () -> jdbcUrl);
        registry.add("spring.flyway.user=", postgreSQLContainer::getPassword);
        registry.add("spring.flyway.password=", postgreSQLContainer::getUsername);
    }
}
