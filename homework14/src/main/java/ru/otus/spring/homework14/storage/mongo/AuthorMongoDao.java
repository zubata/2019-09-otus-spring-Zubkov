package ru.otus.spring.homework14.storage.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework14.domain.mongo.AuthorMongo;

public interface AuthorMongoDao extends MongoRepository<AuthorMongo,String> {

    AuthorMongo getByName(String authorname);
}
