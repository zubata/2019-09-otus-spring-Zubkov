package ru.otus.spring.homework06.storage;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.homework06.domain.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование DAO для работы с жанрами ")
@ExtendWith(SpringExtension.class)
@Import(GenreDaoImpl.class)
@DataJpaTest
class GenreDaoImplTest {
    private static final long COUNT_EXCEPT_INSERT = 2;
    private static final long COUNT_AFTER_INSERT = 3;
    private static final long DEFAULT_ID = 1;
    private static final String DEFAULT_NAME = "Роман";
    private static final String TEST_GENRENAME = "Фэнтези";

    @Autowired
    private GenreDaoImpl genreDaoImpl;
    @Autowired
    private TestEntityManager tem;

    @DisplayName("должно возвращаться корректное число жанров в БД")
    @Test
    void count() {
        assertThat(genreDaoImpl.count()).isEqualTo(COUNT_EXCEPT_INSERT);
    }

    @DisplayName("должен корректно возвратиться жанр по id")
    @Test
    void getById() {
        val actual = genreDaoImpl.getById(DEFAULT_ID);
        val expected = tem.find(Genre.class, DEFAULT_ID);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("должен корректно возвращаться жанр по имени")
    @Test
    void getByName() {
        val actual = genreDaoImpl.getByName(DEFAULT_NAME);
        assertThat(actual).
                hasFieldOrPropertyWithValue("id", 1L).
                hasFieldOrPropertyWithValue("genreName", "Роман");
    }

    @DisplayName("должен корректно вставиться жанр по id")
    @Test
    void insert() {
        long tempId = (long) tem.persistAndGetId(new Genre(TEST_GENRENAME));
        assertThat(tem.find(Genre.class, tempId)).
                hasFieldOrPropertyWithValue("id", tempId).
                hasFieldOrPropertyWithValue("genreName", "Фэнтези");
        assertThat(genreDaoImpl.count()).isEqualTo(COUNT_AFTER_INSERT);
    }

    @DisplayName("должен корректно удалиться жанр в БД")
    @Test
    void delete() {
        long tempId = (long) tem.persistAndGetId(new Genre(TEST_GENRENAME));
        genreDaoImpl.delete(tempId);
        assertThat(tem.find(Genre.class, tempId)).isNull();
        assertThat(genreDaoImpl.count()).isEqualTo(COUNT_EXCEPT_INSERT);
    }

    @DisplayName("должен корректно возвращаться список всех жанров в БД")
    @Test
    void getAll() {
        List<Genre> list = new ArrayList<>();
        list.add(new Genre(1, "Роман"));
        list.add(new Genre(2, "Стихи"));
        assertThat(genreDaoImpl.getAll()).usingFieldByFieldElementComparator().isEqualTo(list);
    }
}