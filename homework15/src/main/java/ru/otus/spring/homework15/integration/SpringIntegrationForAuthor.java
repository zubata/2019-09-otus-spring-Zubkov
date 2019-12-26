package ru.otus.spring.homework15.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;

@IntegrationComponentScan
@SuppressWarnings({"resource", "Duplicates", "InfiniteLoopStatement"})
@ComponentScan
@Configuration
@EnableIntegration
public class SpringIntegrationForAuthor {

    @Bean
    public IntegrationFlow authorFlowForList() {
        return f -> f.handle("authorServiceImpl", "showAllRows");
    }

    @Bean
    public IntegrationFlow authorFlowForFind() { return f -> f.handle("authorServiceImpl", "showById"); }

    @Bean
    public IntegrationFlow authorFlowForInsert() { return f -> f.handle("authorServiceImpl", "insert"); }

    @Bean
    public IntegrationFlow authorFlowForDelete() { return f -> f.handle("authorServiceImpl", "deleteById"); }

}
