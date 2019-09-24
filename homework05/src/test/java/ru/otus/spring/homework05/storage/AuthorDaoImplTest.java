package ru.otus.spring.homework05.storage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.homework05.domain.Author;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование DAO для работы с авторами ")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(AuthorDaoImpl.class)
class AuthorDaoImplTest {
    private static final int COUNT_EXCEPT_INSERT = 2;
    private static final int DEFAULT_ID = 1;
    private static final int NEW_ID = 3;
    private static final int COUNT_AFTER_INSERT = 3;
    private static final Author TEMP = new Author(NEW_ID, "Tolkien");

    @Autowired
    private AuthorDaoImpl authorDaoImpl;

    @DisplayName("должно возвращаться корректное число авторов в БД")
    @Test
    void count() {
        assertThat(authorDaoImpl.count()).isEqualTo(COUNT_EXCEPT_INSERT);
    }

    @DisplayName("должен корректно возвратиться автор по id")
    @Test
    void getById() {
        assertThat(authorDaoImpl.getById(DEFAULT_ID)).hasFieldOrPropertyWithValue("authorName", "Толстой");
    }

    @DisplayName("должен корректно вставиться автор по id")
    @Test
    void insert() {
        authorDaoImpl.insert(TEMP);
        assertThat(authorDaoImpl.getById(NEW_ID)).hasFieldOrPropertyWithValue("authorName", "Tolkien");
        assertThat(authorDaoImpl.count()).isEqualTo(COUNT_AFTER_INSERT);
    }

    @DisplayName("должен корректно удалиться автор в БД")
    @Test
    void delete() {
        authorDaoImpl.delete(NEW_ID);
        assertThat(authorDaoImpl.count()).isEqualTo(COUNT_EXCEPT_INSERT);
    }

    @DisplayName("должен корректно возвращаться список всех авторов в БД")
    @Test
    void getAll() {
        List<Author> list = new ArrayList<>();
        list.add(new Author(1, "Толстой"));
        list.add(new Author(2, "Есенин"));
        assertThat(authorDaoImpl.getAll()).usingFieldByFieldElementComparator().isEqualTo(list);
    }

}