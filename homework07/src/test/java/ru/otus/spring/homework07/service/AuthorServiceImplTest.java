package ru.otus.spring.homework07.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.homework07.domain.Author;
import ru.otus.spring.homework07.service.AuthorService;
import ru.otus.spring.homework07.service.IOService;
import ru.otus.spring.homework07.storage.AuthorDao;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Тестирование сервия AuthorServiceImpl")
@SpringBootTest
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
        verify(authorDao, times(1)).save(any());
    }

    @DisplayName("должен правильно отображаться список авторов (отдаваться в метод output класса ioService)")
    @Test
    void showAllRows() {
        List<Author> list = new ArrayList<>();
        list.add(new Author(1,"Тургенев"));
        list.add(new Author(2,"Cолженицын"));
        given(authorDao.findAll()).willReturn(list);
        authorService.showAllRows();
        verify(ioService,times(1)).output("id =1, автор = 'Тургенев'");
        verify(ioService,times(1)).output("id =2, автор = 'Cолженицын'");
    }

    @DisplayName("должен правильно отображаться автор по id")
    @Test
    void showById() {
        given(ioService.input()).willReturn("1");
        given(authorDao.getById(anyLong())).willReturn(new Author(1,"Тургенев"));
        authorService.showById();
        verify(ioService,times(1)).output("Показать автора с id");
        verify(ioService,times(1)).input();
        verify(authorDao,times(1)).getById(anyLong());
        verify(ioService,times(1)).output("id =1, автор = 'Тургенев'");
    }

    @DisplayName("должен правильно отображаться автор по названию")
    @Test
    void showByName() {
        given(authorDao.getByauthorName(any())).willReturn(new Author(1,"Тургенев"));
        authorService.showByName();
        verify(ioService,times(1)).output("Показать автора с именем");
        verify(ioService,times(1)).input();
        verify(authorDao,times(1)).getByauthorName(any());
        verify(ioService,times(1)).output("id =1, автор = 'Тургенев'");
    }

    @DisplayName("должен удаляться автор по id из БД")
    @Test
    void deleteById() {
        given(ioService.input()).willReturn("1");
        assertThat(authorService.deleteById()).isEqualTo("1");
        verify(ioService,times(1)).output("Удалить автора с id");
        verify(ioService,times(1)).input();
        verify(authorDao,times(1)).deleteById(1L);
    }

    @DisplayName("должен удаляться автор по имени из БД")
    @Test
    void deleteByName() {
        given(ioService.input()).willReturn("Тургенев");
        given(authorDao.getByauthorName(any())).willReturn(new Author(1,"Тургенев"));
        assertThat(authorService.deleteByName()).isEqualTo("Тургенев");
        verify(ioService,times(1)).output("Удалить автора с именем");
        verify(ioService,times(1)).input();
        verify(authorDao,times(1)).getByauthorName(any());
        verify(authorDao,times(1)).deleteById(1L);
    }

    @DisplayName("должно корректно отображаться количество авторов")
    @Test
    void showCount() {
        given(authorDao.count()).willReturn(2L);
        authorService.showCount();
        verify(ioService,times(1)).output("2");
    }


    @DisplayName("должен корректно возвратиться автор, если он есть в БД")
    @Test
    void getAuthor1() {
        given(authorDao.getByauthorName(any())).willReturn(new Author(1,"Тургенев"));
        assertThat(authorService.getAuthor("Тургенев")).isEqualTo(new Author(1,"Тургенев"));
        verify(authorDao,times(1)).getByauthorName(any());
    }

    @DisplayName("должен корректно возвратиться автор, если его нет в БД")
    @Test
    void getAuthor2() {
        given(authorDao.save(any())).willReturn(new Author(1,"Тургенев"));
        assertThat(authorService.getAuthor("Тургенев")).isEqualTo(new Author(1,"Тургенев"));
        verify(authorDao,times(1)).getByauthorName(any());
        verify(authorDao,times(1)).save(any());
    }
}
