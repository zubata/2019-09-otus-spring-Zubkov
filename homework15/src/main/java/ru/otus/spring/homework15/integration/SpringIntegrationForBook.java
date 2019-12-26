package ru.otus.spring.homework15.integration;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import ru.otus.spring.homework15.service.BookService;

@Configuration
@Data
public class SpringIntegrationForBook {

    private final BookService bookService;

    @Bean
    public IntegrationFlow bookFlowForList() { return f -> f.handle("bookServiceImpl", "showAllRows"); }

    @Bean
    public IntegrationFlow bookFlowForFind() {
        return f -> f.handle("bookServiceImpl", "showById");
    }

    @Bean
    public IntegrationFlow bookFlowForInsert() {
        return f -> f
                .transform(bookService::convertToDomain)
                .handle("bookServiceImpl", "insert");
    }

    @Bean
    public IntegrationFlow bookFlowForDelete() {
        return f -> f.handle("bookServiceImpl", "deleteById");
    }

}
