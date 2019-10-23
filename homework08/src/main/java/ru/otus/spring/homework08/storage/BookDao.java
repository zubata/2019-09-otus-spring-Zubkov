package ru.otus.spring.homework08.storage;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework08.domain.Book;

import java.util.List;

public interface BookDao extends MongoRepository<Book,String> {

    Book getById(String id);

    Book getByName(String name);

    List<Book> getByAuthorId(String authorId);

    List<Book> getByGenreListName(String genreName);

    long countByAuthorId(String authorId);
}
