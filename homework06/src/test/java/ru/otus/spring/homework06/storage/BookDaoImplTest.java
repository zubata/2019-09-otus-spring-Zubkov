package ru.otus.spring.homework06.storage;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.homework06.domain.Author;
import ru.otus.spring.homework06.domain.Book;
import ru.otus.spring.homework06.domain.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование DAO для работы с книгами ")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(BookDaoImpl.class)
class BookDaoImplTest {
    private static final long COUNT_EXCEPT_INSERT = 2;
    private static final long COUNT_AFTER_INSERT = 3;
    private static final long DEFAULT_ID = 1;
    private static final String DEFAULT_NAME = "Война и Мир";

    @Autowired
    private TestEntityManager tem;
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
        val actual = bookDaoImpl.getById(DEFAULT_ID);
        val expected = tem.find(Book.class, DEFAULT_ID);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("должна корректно возвратиться книга по названию")
    @Test
    void getByName() {
        val actual = bookDaoImpl.getByName(DEFAULT_NAME);
        assertThat(actual).
                hasFieldOrPropertyWithValue("id", 1L).
                hasFieldOrPropertyWithValue("bookName", "Война и Мир").
                hasFieldOrPropertyWithValue("author", new Author(1, "Толстой")).
                hasFieldOrPropertyWithValue("genre", new Genre(1, "Роман"));
    }

    @DisplayName("должна корректно вставиться книга по id")
    @Test
    void insert() {
        long tempId = (long) tem.persistAndGetId(new Book("The Lord of the Rings", new Author("Tolkien"), new Genre("Фэнтези")));
        assertThat(tem.find(Book.class, tempId)).
                hasFieldOrPropertyWithValue("id", tempId).
                hasFieldOrPropertyWithValue("bookName", "The Lord of the Rings").
                hasFieldOrPropertyWithValue("author", new Author(tempId, "Tolkien")).
                hasFieldOrPropertyWithValue("genre", new Genre(tempId, "Фэнтези"));
        assertThat(bookDaoImpl.count()).isEqualTo(COUNT_AFTER_INSERT);
    }

    @DisplayName("должна корректно удалиться книга в БД")
    @Test
    void delete() {
        long tempId = (long) tem.persistAndGetId(new Book("The Lord of the Rings", new Author("Tolkien"), new Genre("Фэнтези")));
        bookDaoImpl.delete(tempId);
        assertThat(tem.find(Book.class, tempId)).isNull();
        assertThat(bookDaoImpl.count()).isEqualTo(COUNT_EXCEPT_INSERT);
    }

    @DisplayName("должен корректно возвращаться список всех книг в БД")
    @Test
    void getAll() {
        List<Book> list = new ArrayList<>();
        list.add(new Book(1, "Война и Мир", new Author(1, "Толстой"), new Genre(1, "Роман")));
        list.add(new Book(2, "Не стихов златая пена", new Author(2, "Есенин"), new Genre(2, "Стихи")));
        assertThat(bookDaoImpl.getAll()).usingFieldByFieldElementComparator().isEqualTo(list);
    }
}