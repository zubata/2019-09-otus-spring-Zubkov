package ru.otus.spring.homework08.storage;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework08.domain.Author;

public interface AuthorDao extends MongoRepository<Author,String> {

    Author getById(String id);

    Author getByAuthorName(String authorname);

}
