package ru.otus.spring.homework07.storage;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.homework07.domain.Author;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование DAO для работы с авторами ")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(value = SimpleJpaRepository.class)
class AuthorDaoImplTest {
    private static final long COUNT_EXCEPT_INSERT = 2;
    private static final long COUNT_AFTER_INSERT = 3;
    private static final long DEFAULT_ID = 1;
    private static final String DEFAULT_NAME = "Толстой";
    private static final String TEST_AUTHORNAME = "Tolkien";

    @Autowired
    private TestEntityManager tem;

    @Autowired
    private AuthorDao authorDaoImpl;

    @DisplayName("должно возвращаться корректное число авторов в БД")
    @Test
    void count() {
        assertThat(authorDaoImpl.count()).isEqualTo(COUNT_EXCEPT_INSERT);
    }

    @DisplayName("должен корректно возвратиться автор по id")
    @Test
    void getById() {
        val actual = authorDaoImpl.findById(DEFAULT_ID);
        val expected = tem.find(Author.class, DEFAULT_ID);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("должен корректно возвращать автора по названию")
    @Test
    void getByName() {
        val actual = authorDaoImpl.findByauthorName(DEFAULT_NAME);
        assertThat(actual)
                .hasFieldOrPropertyWithValue("authorName", "Толстой")
                .hasFieldOrPropertyWithValue("id", 1L);
    }

    @DisplayName("должен корректно вставиться автор по id")
    @Test
    void insert() {
        long tempId = (long) tem.persistAndGetId(new Author(TEST_AUTHORNAME));
        assertThat(tem.find(Author.class, tempId)).
                hasFieldOrPropertyWithValue("id", tempId).
                hasFieldOrPropertyWithValue("authorName", "Tolkien");
        assertThat(authorDaoImpl.count()).isEqualTo(COUNT_AFTER_INSERT);
    }

    @DisplayName("должен корректно удалиться автор в БД")
    @Test
    void delete() {
        long tempId = (long) tem.persistAndGetId(new Author(TEST_AUTHORNAME));
        authorDaoImpl.deleteById(tempId);
        assertThat(tem.find(Author.class, tempId)).isNull();
        assertThat(authorDaoImpl.count()).isEqualTo(COUNT_EXCEPT_INSERT);
    }

    @DisplayName("должен корректно возвращаться список всех авторов в БД")
    @Test
    void getAll() {
        List<Author> list = new ArrayList<>();
        list.add(new Author(1, "Толстой"));
        list.add(new Author(2, "Есенин"));
        assertThat(authorDaoImpl.findAll()).usingFieldByFieldElementComparator().isEqualTo(list);
    }

}