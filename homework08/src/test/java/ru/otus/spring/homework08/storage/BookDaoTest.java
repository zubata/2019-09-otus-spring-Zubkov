package ru.otus.spring.homework08.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.homework08.domain.Author;
import ru.otus.spring.homework08.domain.Book;
import ru.otus.spring.homework08.domain.Genre;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Тестирование DAO для работы с книгами ")
@ComponentScan("ru.otus.spring.homework08.storage")
@DataMongoTest
class BookDaoTest {
    private static final String DEFAULT_BOOKNAME = "The Lord of The Rings";
    private static final String DEFAULT_ID = "1";
    private static final Author DEFAULT_AUTHOR = new Author("Tolkien");
    private static final Genre DEFAULT_GENRE = new Genre("Fantasy");
    private static final Book testBook = new Book(DEFAULT_ID,DEFAULT_BOOKNAME,DEFAULT_AUTHOR,DEFAULT_GENRE);

    @Autowired
    private BookDao bookDao;

    @BeforeEach
    void setUp() {
        bookDao.save(testBook);
    }

    @DisplayName("должна корректно вернуться книга по ID")
    @Test
    void getById() {
        assertThat(bookDao.getById(DEFAULT_ID)).hasFieldOrPropertyWithValue("id",DEFAULT_ID)
                .hasFieldOrPropertyWithValue("bookName",DEFAULT_BOOKNAME)
                .hasFieldOrPropertyWithValue("author",DEFAULT_AUTHOR)
                .hasFieldOrPropertyWithValue("genre",DEFAULT_GENRE);
    }

    @DisplayName("должна корректно вернуться книга по имени")
    @Test
    void getByBookName() {
        assertThat(bookDao.getByBookName(DEFAULT_BOOKNAME)).hasFieldOrPropertyWithValue("id",DEFAULT_ID)
                .hasFieldOrPropertyWithValue("bookName",DEFAULT_BOOKNAME)
                .hasFieldOrPropertyWithValue("author",DEFAULT_AUTHOR)
                .hasFieldOrPropertyWithValue("genre",DEFAULT_GENRE);
    }
}
