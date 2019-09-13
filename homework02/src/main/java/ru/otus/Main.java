package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.service.PollServiceImpl;

import java.io.IOException;

@Configuration
@ComponentScan(basePackages = "ru.otus")

public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        PollServiceImpl poll = context.getBean(PollServiceImpl.class);
        poll.testing();
    }
}
