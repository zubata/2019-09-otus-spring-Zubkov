package ru.otus.spring.homework05.storage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.homework05.domain.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование DAO для работы с жанрами ")
@ExtendWith(SpringExtension.class)
@Import(GenreDaoImpl.class)
@JdbcTest
class GenreDaoImplTest {
    private static final int COUNT_EXCEPT_INSERT = 2;
    private static final int COUNT_AFTER_INSERT = 3;
    private static final int NEW_ID = 3;
    private static final int DEFAULT_ID = 1;
    private static final String DEFAULT_NAME = "Роман";
    private static final Genre TEMP = new Genre("Фэнтези");

    @Autowired
    private GenreDaoImpl genreDaoImpl;

    @DisplayName("должно возвращаться корректное число жанров в БД")
    @Test
    void count() {
        assertThat(genreDaoImpl.count()).isEqualTo(COUNT_EXCEPT_INSERT);
    }

    @DisplayName("должен корректно возвратиться жанр по id")
    @Test
    void getById() {
        assertThat(genreDaoImpl.getById(DEFAULT_ID)).
                hasFieldOrPropertyWithValue("id", 1L).
                hasFieldOrPropertyWithValue("genreName", "Роман");
    }

    @DisplayName("должен корректно возвращаться жанр по имени")
    @Test
    void getByName() {
        assertThat(genreDaoImpl.getByName(DEFAULT_NAME)).
                hasFieldOrPropertyWithValue("id", 1L).
                hasFieldOrPropertyWithValue("genreName", "Роман");
    }

    @DisplayName("должен корректно вставиться жанр по id")
    @Test
    void insert() {
        genreDaoImpl.insert(TEMP);
        assertThat(genreDaoImpl.getById(NEW_ID)).
                hasFieldOrPropertyWithValue("id", 3L).
                hasFieldOrPropertyWithValue("genreName", "Фэнтези");
        assertThat(genreDaoImpl.count()).isEqualTo(COUNT_AFTER_INSERT);
    }

    @DisplayName("должен корректно удалиться жанр в БД")
    @Test
    void delete() {
        genreDaoImpl.delete(NEW_ID);
        assertThat(genreDaoImpl.count()).isEqualTo(COUNT_EXCEPT_INSERT);
    }

    @DisplayName("должен корректно возвращаться список всех жанров в БД")
    @Test
    void getAll() {
        List<Genre> list = new ArrayList<>();
        list.add(new Genre(1,"Роман"));
        list.add(new Genre(2,"Стихи"));
        assertThat(genreDaoImpl.getAll()).usingFieldByFieldElementComparator().isEqualTo(list);
    }
}