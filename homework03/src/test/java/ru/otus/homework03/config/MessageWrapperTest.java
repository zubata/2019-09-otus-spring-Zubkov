package ru.otus.homework03.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Тест обёртки MessageWrapper ")
@SpringBootTest
class MessageWrapperTest {

    @Autowired
    private MessageWrapper mw;
    @MockBean
    private AppSettings appSettings;

    @DisplayName(" метод getMessage()")
    @Test
    void getMessage1() {
        given(appSettings.getLocale()).willReturn(new Locale("en","EN"));
        String message = "input.first.name";
        assertEquals("Enter First name",mw.getMessage(message));
        verify(appSettings,times(1)).getLocale();
    }

    @DisplayName(" метод getMessage(String source, String... values)")
    @Test
    void getMessage2() {
        given(appSettings.getLocale()).willReturn(new Locale("en","EN"));
        String message = "intro.msg";
        String[] parametrs = {"Oleg","Zubkov"};
        assertEquals("Test is taken by Oleg Zubkov",mw.getMessage(message,parametrs));
        verify(appSettings,times(1)).getLocale();
    }
}