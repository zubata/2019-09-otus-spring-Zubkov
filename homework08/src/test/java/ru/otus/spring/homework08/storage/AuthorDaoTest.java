package ru.otus.spring.homework08.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.homework08.domain.Author;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Тестирование DAO для работы с авторами ")
@ComponentScan("ru.otus.spring.homework08.storage")
@DataMongoTest
class AuthorDaoTest {
    private static final String DEFAULT_ID = "1";
    private static final String DEFAULT_AUTHORNAME = "Tolkien";
    private static final Author testAuthor = new Author(DEFAULT_ID,DEFAULT_AUTHORNAME);

    @Autowired
    private AuthorDao authorDao;

    @BeforeEach
    void setUp() {
        authorDao.save(testAuthor);
    }

    @DisplayName("должен корректно вернуться автор по ID")
    @Test
    void getById() {
        assertThat(authorDao.getById(DEFAULT_ID)).hasFieldOrPropertyWithValue("id",DEFAULT_ID)
                .hasFieldOrPropertyWithValue("authorName",DEFAULT_AUTHORNAME);
    }

    @DisplayName("должен корректно вернуться автор по имени")
    @Test
    void getByAuthorName() {
        Author tt = authorDao.getByAuthorName(DEFAULT_AUTHORNAME);
        assertThat(authorDao.getByAuthorName(DEFAULT_AUTHORNAME)).hasFieldOrPropertyWithValue("id",DEFAULT_ID)
                .hasFieldOrPropertyWithValue("authorName",DEFAULT_AUTHORNAME);
    }
}