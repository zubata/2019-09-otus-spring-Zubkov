package ru.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LanguageService {

    public LanguageService(@Value("${language}") String lang) {
        if(lang.equals("ru")) Locale.setDefault(new Locale(lang,"RU"));
        else if (lang.equals("en")) Locale.setDefault(new Locale("en","EN"));
        else new RuntimeException("Не правильно введён язык");
    }

}
