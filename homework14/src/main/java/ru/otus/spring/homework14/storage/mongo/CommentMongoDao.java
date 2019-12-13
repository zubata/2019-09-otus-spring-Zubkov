package ru.otus.spring.homework14.storage.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework14.domain.mongo.CommentMongo;

public interface CommentMongoDao extends MongoRepository<CommentMongo,String> {
}
