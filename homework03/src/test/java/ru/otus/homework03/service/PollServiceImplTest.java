package ru.otus.homework03.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework03.database.QuestionDao;
import ru.otus.homework03.domain.Person;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Тест класса PollServiceImpl ")
@SpringBootTest
class PollServiceImplTest {

    @Autowired
    PollService pollService;
    @MockBean
    IOService ioService;
    @MockBean
    PersonService personService;


    @DisplayName("метод testing")
    @Test
    void testing() throws IOException {
        given(personService.getPerson()).willReturn(new Person("Ivan","Ivanov"));
        given(ioService.input()).willReturn("1").willReturn("2").willReturn("3").willReturn("4").willReturn("5");
        pollService.testing();
        verify(ioService,times(7)).output(anyString());
        verify(ioService,times(5)).input();
        verify(personService,times(1)).getPerson();
    }
}