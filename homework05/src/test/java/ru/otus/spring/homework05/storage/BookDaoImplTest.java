package ru.otus.spring.homework05.storage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.homework05.domain.Book;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование DAO для работы с книгами ")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(BookDaoImpl.class)
class BookDaoImplTest {
    private static final int COUNT_EXCEPT_INSERT = 2;
    private static final int NEW_ID = 3;
    private static final int COUNT_AFTER_INSERT = 3;
    private static final int DEFAULT_ID = 1;
    private static final Book TEMP = new Book(NEW_ID, "The Lord of the Rings", "Tolkien");

    @Autowired
    private BookDaoImpl bookDaoImpl;

    @DisplayName("должно возвращаться корректное число книг в БД")
    @Test
    void count() {
        assertThat(bookDaoImpl.count()).isEqualTo(COUNT_EXCEPT_INSERT);
    }

    @DisplayName("должна корректно возвратиться книга по id")
    @Test
    void getById() {
        assertThat(bookDaoImpl.getById(DEFAULT_ID)).
                hasFieldOrPropertyWithValue("bookName", "Война и Мир").
                hasFieldOrPropertyWithValue("authorName", "Толстой");
    }

    @DisplayName("должна корректно вставиться книга по id")
    @Test
    void insert() {
        bookDaoImpl.insert(TEMP);
        assertThat(bookDaoImpl.getById(NEW_ID)).
                hasFieldOrPropertyWithValue("bookName", "The Lord of the Rings").
                hasFieldOrPropertyWithValue("authorName", "Tolkien");
        assertThat(bookDaoImpl.count()).isEqualTo(COUNT_AFTER_INSERT);
    }

    @DisplayName("должна корректно удалиться книга в БД")
    @Test
    void delete() {
        bookDaoImpl.delete(NEW_ID);
        assertThat(bookDaoImpl.count()).isEqualTo(COUNT_EXCEPT_INSERT);
    }

    @DisplayName("должен корректно возвращаться список всех книг в БД")
    @Test
    void getAll() {
        List<Book> list = new ArrayList<>();
        list.add(new Book(1, "Война и Мир", "Толстой"));
        list.add(new Book(2, "Не стихов златая пена", "Есенин"));
        assertThat(bookDaoImpl.getAll()).usingFieldByFieldElementComparator().isEqualTo(list);
    }
}