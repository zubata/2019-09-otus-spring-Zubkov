package ru.otus.spring.homework15.integration;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import ru.otus.spring.homework15.service.CommentService;

@Configuration
@Data
public class SpringIntegrationForComment {

    private final CommentService commentService;

    @Bean
    public IntegrationFlow commentFlowForListByBook() { return f -> f.handle("commentServiceImpl", "showByBook"); }

    @Bean
    public IntegrationFlow commentFlowForInsert() {
        return f -> f
                .transform(commentService::convertToDomain)
                .handle("commentServiceImpl","insert");
    }

    @Bean
    public IntegrationFlow commentFlowForDelete() { return f -> f.handle("commentServiceImpl","deleteById"); }
}
