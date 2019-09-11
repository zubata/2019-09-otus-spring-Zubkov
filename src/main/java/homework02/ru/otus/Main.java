package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.domain.Person;
import ru.otus.service.PersonServiceGet;
import ru.otus.service.ServicePollImpl;

import java.io.IOException;

@Configuration
@ComponentScan(basePackages = "ru.otus")

public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        PersonServiceGet db = context.getBean(PersonServiceGet.class);
        ServicePollImpl poll = context.getBean(ServicePollImpl.class);
        Person ya = db.getPerson();
        poll.testing(ya);
    }
}
