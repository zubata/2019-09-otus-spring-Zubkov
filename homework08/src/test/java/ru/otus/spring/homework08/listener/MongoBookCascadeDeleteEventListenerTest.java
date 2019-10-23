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
import ru.otus.spring.homework08.domain.Comment;
import ru.otus.spring.homework08.domain.Genre;
import ru.otus.spring.homework08.service.IOService;
import ru.otus.spring.homework08.storage.BookDao;
import ru.otus.spring.homework08.storage.CommentDao;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Проверка работы каскадного удаления комментариев при удалении книги")
@DataMongoTest
@ComponentScan("ru.otus.spring.homework08.listener")
class MongoBookCascadeDeleteEventListenerTest {
    private static final String DEFAULT_BOOKNAME = "The Lord of The Rings";
    private static final String DEFAULT_ID = "1";
    private static final Author DEFAULT_AUTHOR = new Author("Tolkien");
    private static final Genre DEFAULT_GENRE = new Genre("Fantasy");
    private static final Book TESTBOOK = new Book(DEFAULT_ID, DEFAULT_BOOKNAME, DEFAULT_AUTHOR, DEFAULT_GENRE);
    private static final Comment TESTCOMMENT = new Comment(TESTBOOK);

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private BookDao bookDao;
    @MockBean
    private IOService ioService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        mongoTemplate.save(TESTBOOK);
        TESTCOMMENT.setComment("Well");
        mongoTemplate.save(TESTCOMMENT);
    }

    @DisplayName("MongoBookCascadeDeleteEventListener должен удалять все комментарии при удалении книги")
    @Test
    void onBeforeDelete1() {
        assertThat(commentDao.count()).isEqualTo(1);
        bookDao.deleteById(DEFAULT_ID);
        assertThat(bookDao.getByName(DEFAULT_BOOKNAME)).isNull();
        assertThat(commentDao.count()).isEqualTo(0);
    }

}