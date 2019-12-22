package ru.otus.spring.homework15.storage;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;
import ru.otus.spring.homework15.domain.Book;

import java.util.List;

public interface BookDao extends JpaRepository<Book,Long> {

    @EntityGraph(value = "BookWithAuthorAndGenre")
    Book getById(long id);

    @EntityGraph(value = "BookWithAuthorAndGenre")
    Book getByName(String bookname);

    @Override
    @EntityGraph(value = "BookWithAuthorAndGenre")
    List<Book> findAll();

}
