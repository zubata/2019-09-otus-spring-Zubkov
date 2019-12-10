package ru.otus.spring.homework14.storage;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework14.domain.Author;

public interface AuthorDao extends MongoRepository<Author,String> {

    Author getByName(String authorname);
}
