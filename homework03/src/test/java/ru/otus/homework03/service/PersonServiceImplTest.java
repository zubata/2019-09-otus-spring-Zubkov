package ru.otus.homework03.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework03.config.MessageWrapper;
import ru.otus.homework03.domain.Person;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Тест класса PersonServiceImpl ")
@SpringBootTest
class PersonServiceImplTest {

    @Autowired
    private PersonService personService;
    @MockBean
    private IOService ioService;

    @DisplayName("метод getPerson")
    @Test
    void getPerson() throws IOException {
        given(ioService.input()).willReturn("Ivan").willReturn("Ivanov");
        Person test = personService.getPerson();
        assertThat(test).isEqualToComparingFieldByField(new Person("Ivan","Ivanov"));
        verify(ioService,times(2)).input();
        verify(ioService,times(2)).output(anyString());
    }
}