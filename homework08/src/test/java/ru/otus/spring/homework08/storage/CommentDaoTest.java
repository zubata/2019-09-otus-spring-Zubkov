package ru.otus.spring.homework08.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.homework08.domain.Author;
import ru.otus.spring.homework08.domain.Book;
import ru.otus.spring.homework08.domain.Comment;
import ru.otus.spring.homework08.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование DAO для работы с комментариями ")
@ComponentScan("ru.otus.spring.homework08.storage")
@DataMongoTest
class CommentDaoTest {
    private static final String DEFAULT_ID = "1";
    private static final String DEFAULT_BOOKNAME = "The Lord of The Rings";
    private static final Book DEFAULT_BOOK = new Book(DEFAULT_ID,DEFAULT_BOOKNAME, new Author("Tolkien"), new Genre("Fantasy"));
    private static final String DEFAULT_COMMENT = "Well";
    private static final Comment DEFAULT_COMMENT_OBJECT = new Comment(DEFAULT_ID, DEFAULT_BOOK, DEFAULT_COMMENT);

    @Autowired
    private CommentDao commentDao;

    @BeforeEach
    void setUp() {
        commentDao.save(DEFAULT_COMMENT_OBJECT);
    }

    @Test
    void getById() {
        assertThat(commentDao.getById(DEFAULT_ID)).hasFieldOrPropertyWithValue("id",DEFAULT_ID)
                .hasFieldOrPropertyWithValue("book",DEFAULT_BOOK)
                .hasFieldOrPropertyWithValue("comment",DEFAULT_COMMENT);
    }

    @Test
    void getByBookName() {
        assertThat(commentDao.getByBookName(DEFAULT_BOOKNAME)).containsOnlyOnce(DEFAULT_COMMENT_OBJECT);
    }

    @Test
    void getByBookId() {
        assertThat(commentDao.getByBookId(DEFAULT_ID)).containsOnlyOnce(DEFAULT_COMMENT_OBJECT);
    }
}