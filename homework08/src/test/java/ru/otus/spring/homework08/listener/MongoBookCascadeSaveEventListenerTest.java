package ru.otus.spring.homework08.listener;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework08.domain.Author;
import ru.otus.spring.homework08.domain.Book;
import ru.otus.spring.homework08.domain.Genre;
import ru.otus.spring.homework08.service.IOService;
import ru.otus.spring.homework08.storage.AuthorDao;
import ru.otus.spring.homework08.storage.BookDao;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Проверка работы каскадного сохранения авторов при сохранени книги")
@DataMongoTest
@ComponentScan("ru.otus.spring.homework08.listener")
class MongoBookCascadeSaveEventListenerTest {
    private static final String DEFAULT_BOOKNAME = "The Lord of The Rings";
    private static final String DEFAULT_ID = "1";
    private static final Author DEFAULT_AUTHOR = new Author("1","Tolkien");
    private static final Genre DEFAULT_GENRE = new Genre("Fantasy");
    private static final Book TESTBOOK = new Book(DEFAULT_ID, DEFAULT_BOOKNAME, DEFAULT_AUTHOR, DEFAULT_GENRE);

    @MockBean
    private IOService ioService;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private BookDao bookDao;

    @DisplayName("MongoBookCascadeSaveEventListenerTest должен сохранить автора при сохранении книги")
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void onBeforeConvert() {
        assertThat(authorDao.count()).isEqualTo(0);
        bookDao.save(TESTBOOK);
        assertThat(authorDao.count()).isEqualTo(1);
        assertThat(authorDao.getByAuthorName(DEFAULT_AUTHOR.getAuthorName())).isEqualTo(DEFAULT_AUTHOR);
        assertThat(bookDao.getByBookName(DEFAULT_BOOKNAME)).isEqualTo(TESTBOOK);
    }
}