package ru.otus.spring.homework18.storage;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework18.domain.Book;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Long> {

    @EntityGraph(value = "BookWithAuthorAndGenre")
    Book getById(long id);

    @EntityGraph(value = "BookWithAuthorAndGenre")
    Book getByName(String bookname);

    @Override
    @EntityGraph(value = "BookWithAuthorAndGenre")
    List<Book> findAll();

}
