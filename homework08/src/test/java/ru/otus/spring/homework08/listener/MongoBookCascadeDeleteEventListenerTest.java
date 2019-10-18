package ru.otus.spring.homework08.listener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.homework08.domain.Author;
import ru.otus.spring.homework08.domain.Book;
import ru.otus.spring.homework08.domain.Comment;
import ru.otus.spring.homework08.domain.Genre;
import ru.otus.spring.homework08.exceptions.NoDeleteBookException;
import ru.otus.spring.homework08.service.IOService;
import ru.otus.spring.homework08.storage.BookDao;
import ru.otus.spring.homework08.storage.CommentDao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

    @BeforeEach
    void setUp() {
        bookDao.save(TESTBOOK);
        TESTCOMMENT.setComment("Well");
        commentDao.save(TESTCOMMENT);
    }

    @DisplayName("MongoBookCascadeDeleteEventListener должен удалять все комментарии при удалении книги")
    @Test
    void onBeforeDelete1() {
        given(ioService.input()).willReturn("y");
        assertThat(commentDao.count()).isEqualTo(1);
        bookDao.deleteById(DEFAULT_ID);
        assertThat(bookDao.getByBookName(DEFAULT_BOOKNAME)).isNull();
        assertThat(commentDao.count()).isEqualTo(0);
        verify(ioService, times(1)).output(any());
        verify(ioService, times(1)).input();
    }

    @DisplayName("MongoBookCascadeDeleteEventListener не должен удалять книгу и все комментарии при удалении книги, так же будет прокинуто исключение")
    @Test
    void onBeforeDelete2() {
        given(ioService.input()).willReturn("n");
        assertThat(commentDao.count()).isEqualTo(1);
        assertThrows(NoDeleteBookException.class, () -> bookDao.deleteById(DEFAULT_ID));
        assertThat(bookDao.getByBookName(DEFAULT_BOOKNAME)).isEqualTo(TESTBOOK);
        assertThat(commentDao.count()).isEqualTo(1);
        verify(ioService, times(1)).output(any());
        verify(ioService, times(1)).input();
    }


}