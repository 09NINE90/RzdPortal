package com.example.rzd.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.rzd.repository")
public class JpaConfig {
    // Дополнительная конфигурация JPA, если необходимо
}