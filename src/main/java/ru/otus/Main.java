package ru.otus;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.domain.Person;
import ru.otus.service.ServicePollImpl;
import ru.otus.service.PersonServiceGet;

import java.io.IOException;

@Configuration
@ComponentScan(basePackages = "ru.otus")
@PropertySource("classpath:app.properties")


public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        PersonServiceGet db = context.getBean(PersonServiceGet.class);
        ServicePollImpl poll = context.getBean(ServicePollImpl.class);
        Person ya = db.getPerson();
        poll.testing(ya);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

}
