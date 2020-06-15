package ru.otus.project.rest;

import com.fasterxml.jackson.databind.Module;
import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PageHelper {

    @Bean
    public Module pageJacksonModule() {
        return new PageJacksonModule();
    }
}
