package ru.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Locale;

@Configuration
@PropertySource("classpath:app.properties")
public class AppConfig {

    private final String filename;
    private Locale locale;

    public AppConfig(@Value("${fileway}") String fileway, @Value("${filetype}") String filetype, @Value("${language}") String language) {
        filename = fileway + language + filetype;
        locale = setLanguage(language);
    }

    public Locale setLanguage(String language) {
        if(language.equals("ru")) return new Locale(language,"RU");
        else if (language.equals("en")) return new Locale("en","EN");
        else new RuntimeException("Не правильно введён язык");
        return null;
    }

    public String getFilename() {
        return filename;
    }

    public Locale getLocale() {
        return locale;
    }
}
