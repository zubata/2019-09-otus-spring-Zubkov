package ru.otus.homework03.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("application")
public class PropetiesConfig {

    private String fileway;
    private String filetype;
    private String language;

    public String getFileway() { return fileway; }
    public void setFileway(String fileway) { this.fileway = fileway; }
    public String getFiletype() { return filetype; }
    public void setFiletype(String filetype) { this.filetype = filetype; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) {this.language=language;}

    public String getFilename() { return fileway + language + filetype; }
}
