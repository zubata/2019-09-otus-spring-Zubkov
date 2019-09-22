package ru.otus.homework04.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Проверка AppSettings ")
@SpringBootTest
class AppSettingsTest {

    @Autowired
    private AppSettings appSettings;

    @DisplayName("метод getFilename")
    @Test
    void testgetFilename() {
        assertEquals("csv/test_en.csv",appSettings.getFilename());
    }

    @DisplayName("метод getLocale")
    @Test
    void getLocale() {
        appSettings.setLocale("en");
        assertThat(appSettings.getLocale()).isEqualTo(new Locale("en","EN"));
    }

    @DisplayName("метод setLocale")
    @Test
    void setLocale() {
        appSettings.setLocale("ru");
        assertThat(appSettings.getLocale()).isEqualTo(new Locale("ru","RU"));
    }
}