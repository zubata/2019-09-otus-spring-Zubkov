package ru.otus.spring.homework14.storage.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework14.domain.mongo.BookMongo;

public interface BookMongoDao extends MongoRepository<BookMongo,String> {

    BookMongo getByName(String book);

}
