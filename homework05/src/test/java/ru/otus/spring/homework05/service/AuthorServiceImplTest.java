package ru.otus.spring.homework05.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.homework05.storage.AuthorDao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("Тестирование сервия AuthorServiceImpl")
@SpringBootTest
@ComponentScan("ru.otus.spring.homework05.service")
class AuthorServiceImplTest {

    @Autowired
    private AuthorService authorService;
    @MockBean
    private IOService ioService;
    @MockBean
    private AuthorDao authorDao;

    @Test
    void insert() {
        given(ioService.input()).willReturn("Cолженицын");
        //doAnswer(any()).when(authorDao).insert(any());
        doReturn(any()).when(authorDao).insert(any());
        assertThat(authorService.insert()).isEqualTo("Солженицын");
        verify(ioService, times(1)).output(any());
        verify(ioService, times(1)).input();
        verify(authorDao, times(1)).insert(any());
    }

    @Test
    void showAllRows() {

    }

    @Test
    void showById() {
    }

    @Test
    void delete() {
    }

    @Test
    void showCount() {
    }
}