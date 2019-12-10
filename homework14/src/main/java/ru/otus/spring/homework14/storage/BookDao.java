package ru.otus.spring.homework14.storage;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework14.domain.Book;

public interface BookDao extends MongoRepository<Book,String> {

    Book getByName(String book);

}
