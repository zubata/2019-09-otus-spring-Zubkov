package ru.otus.spring.homework08.storage;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework08.domain.Book;

public interface BookDao extends MongoRepository<Book,String> {

    Book getById(String id);

    Book getByBookName(String bookname);
}
