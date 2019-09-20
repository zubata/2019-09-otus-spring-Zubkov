package ru.otus.homework03.config;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageWrapper {
    private final MessageSource ms;
    private final AppSettings appSettings;

    public MessageWrapper(AppSettings appSettings, MessageSource ms) {
        this.ms = ms;
        this.appSettings = appSettings;
    }

    public String getMessage(String source) {
        return ms.getMessage(source, null, appSettings.getLocale());
    }

    public String getMessage(String source, String... values) { return ms.getMessage(source, values, appSettings.getLocale());}

}
