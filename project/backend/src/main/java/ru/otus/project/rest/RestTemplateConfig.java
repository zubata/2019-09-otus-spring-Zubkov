package ru.otus.project.rest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@Retryable
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(15))
                .build();
    }
}

