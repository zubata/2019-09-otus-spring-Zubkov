package ru.otus.spring.homework08.listener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.homework08.domain.Author;
import ru.otus.spring.homework08.domain.Book;
import ru.otus.spring.homework08.domain.Genre;
import ru.otus.spring.homework08.service.IOService;
import ru.otus.spring.homework08.storage.AuthorDao;
import ru.otus.spring.homework08.storage.BookDao;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Проверка работы каскадного удаления книг при удалении автора")
@DataMongoTest
@ComponentScan("ru.otus.spring.homework08.listener")
class MongoAuthorCascadeDeleteBookEventListenerTest {
    private static final String DEFAULT_BOOKNAME = "The Lord of The Rings";
    private static final String DEFAULT_ID = "1";
    private static final Author DEFAULT_AUTHOR = new Author(DEFAULT_ID,"Tolkien");
    private static final Genre DEFAULT_GENRE = new Genre("Fantasy");
    private static final Book TESTBOOK = new Book(DEFAULT_BOOKNAME, DEFAULT_AUTHOR, DEFAULT_GENRE);

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private BookDao bookDao;
    @MockBean
    private IOService ioService;
    @Autowired
    private AuthorDao authorDao;

    @BeforeEach
    void setUp() {
        mongoTemplate.save(TESTBOOK);
    }

    @DisplayName("MongoAuthorCascadeDeleteBookEventListener должен удалять все книги при удалении автора")
    @Test
    void onBeforeDelete() {
        assertThat(authorDao.count()).isEqualTo(1);
        assertThat(bookDao.count()).isEqualTo(1);
        authorDao.deleteById(DEFAULT_ID);
        assertThat(authorDao.getById(DEFAULT_ID)).isNull();
        assertThat(authorDao.count()).isEqualTo(0);
        assertThat(bookDao.count()).isEqualTo(0);
    }
}