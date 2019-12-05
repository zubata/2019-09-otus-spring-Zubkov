package ru.otus.spring.homework13.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework13.domain.Author;
import ru.otus.spring.homework13.storage.AuthorDao;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@DisplayName("Тестирование сервиса AuthorServiceImpl")
@SpringBootTest
class AuthorServiceImplTest {
    private static final long TEST_ID = 1;
    private static final String TEST_NAME = "Толстой";
    private static final Author TEST_AUTHOR = new Author(1,TEST_NAME);

    @Autowired
    private AuthorService authorService;

    @MockBean
    private AuthorDao authorDao;

    @DisplayName("должен вставляться объект в БД")
    @Test
    void insert() {
        assertThat(authorService.insert(TEST_AUTHOR)).isEqualTo("Автор Толстой не был добавлен");
        verify(authorDao, times(1)).save(any());
    }

    @DisplayName("должен правильно отображаться список авторов (отдаваться в метод output класса ioService)")
    @Test
    void showAllRows() {
        List<Author> list = new ArrayList<>();
        list.add(new Author(1,"Тургенев"));
        list.add(new Author(2,"Cолженицын"));
        given(authorDao.findAll()).willReturn(list);
        assertThat(authorService.showAllRows()).usingFieldByFieldElementComparator().isEqualTo(list);
    }

    @DisplayName("должен правильно отображаться автор по id")
    @Test
    void showById() {
        given(authorDao.getById(anyLong())).willReturn(TEST_AUTHOR);
        assertThat(authorService.showById(TEST_ID)).isEqualTo(TEST_AUTHOR);
        verify(authorDao,times(1)).getById(anyLong());
    }

    @DisplayName("должен удаляться автор по id из БД")
    @Test
    void deleteById() {
        given(authorDao.getById(anyLong())).willReturn(TEST_AUTHOR);
        assertThat(authorService.deleteById(TEST_ID)).isEqualTo("Автор с id 1 удален из БД");
        verify(authorDao,times(1)).deleteById(1L);
    }



}
