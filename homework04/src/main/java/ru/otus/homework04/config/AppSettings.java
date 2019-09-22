package ru.otus.homework04.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
@ConfigurationProperties("application")
public class AppSettings {

    private String filename;
    private Locale locale;
    private String fileway;
    private String filetype;
    private String language;

    @PostConstruct
    public void init() {
        filename = fileway + language + filetype;
        setLocale(language);
    }

    public String getFilename() { return filename; }

    public void setLocale(String language) {
        if(language.equals("ru")) locale = new Locale(language,"RU");
        else if (language.equals("en")) locale = new Locale("en","EN");
        else new RuntimeException("Не правильно введён язык");
    }
    public Locale getLocale() {
        return locale;
    }

    public void setFileway(String fileway) { this.fileway = fileway; }
    public void setFiletype(String filetype) { this.filetype = filetype; }
    public void setLanguage(String language) { this.language = language; }

}
