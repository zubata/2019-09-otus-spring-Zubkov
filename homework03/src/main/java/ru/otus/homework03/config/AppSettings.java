package ru.otus.homework03.config;

import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AppSettings {

    private final String filename;
    private Locale locale;

    public AppSettings(PropetiesConfig pf) {
        this.filename = pf.getFilename();
        setLocale(pf.getLanguage());
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

}
