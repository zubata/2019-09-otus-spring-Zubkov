package ru.otus.spring.homework05.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.homework05.domain.Author;
import ru.otus.spring.homework05.storage.AuthorDao;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Тестирование сервия AuthorServiceImpl")
@SpringBootTest()
class AuthorServiceImplTest {

    @Autowired
    private AuthorService authorService;
    @MockBean
    private IOService ioService;
    @MockBean
    private AuthorDao authorDao;

    @DisplayName("должен вставляться объект в БД")
    @Test
    void insert() {
        given(ioService.input()).willReturn("Cолженицын");
        assertThat(authorService.insert()).isEqualTo("Cолженицын");
        verify(ioService, times(1)).output(any());
        verify(ioService, times(1)).input();
        verify(authorDao, times(1)).insert(any());
    }

    @DisplayName("должен правильно отображаться список авторов (отдаваться в метод output класса ioService)")
    @Test
    void showAllRows() {
        List<Author> list = new ArrayList<>();
        list.add(new Author(1,"Тургенев"));
        list.add(new Author(2,"Cолженицын"));
        given(authorDao.getAll()).willReturn(list);
        authorService.showAllRows();
        verify(ioService,times(1)).output("1 Тургенев");
        verify(ioService,times(1)).output("2 Cолженицын");
    }

    @DisplayName("должен правильно отображаться автор по id")
    @Test
    void showById() {
        given(ioService.input()).willReturn("1");
        given(authorDao.getById(1)).willReturn(new Author(1,"Тургенев"));
        authorService.showById();
        verify(ioService,times(1)).output("Показать автора с id");
        verify(ioService,times(1)).input();
        verify(authorDao,times(1)).getById(1);
        verify(ioService,times(1)).output("1 Тургенев");
    }

    @DisplayName("должен удаляться автор из БД")
    @Test
    void delete() {
        given(ioService.input()).willReturn("1");
        assertThat(authorService.delete()).isEqualTo("1");
        verify(ioService,times(1)).output("Удалить автора с id");
        verify(ioService,times(1)).input();
        verify(authorDao,times(1)).delete(1);
    }

    @DisplayName("должно корректно отображаться количество авторов")
    @Test
    void showCount() {
        given(authorDao.count()).willReturn(2);
        authorService.showCount();
        verify(ioService,times(1)).output("2");
    }


    @DisplayName("должен корректно возвратиться автор, если он есть в БД")
    @Test
    void getAuthor1() {
        given(authorDao.getByName(any())).willReturn(new Author(1,"Тургенев"));
        assertThat(authorService.getAuthor("Тургенев")).isEqualTo(new Author(1,"Тургенев"));
        verify(authorDao,times(1)).getByName(any());
    }

    @DisplayName("должен корректно возвратиться автор, если его нет в БД")
    @Test
    void getAuthor2() {
        given(authorDao.getByName(any())).willThrow(new EmptyResultDataAccessException(0)).willReturn(new Author(1,"Тургенев"));
        assertThat(authorService.getAuthor("Тургенев")).isEqualTo(new Author(1,"Тургенев"));
        verify(authorDao,times(2)).getByName(any());
    }
}