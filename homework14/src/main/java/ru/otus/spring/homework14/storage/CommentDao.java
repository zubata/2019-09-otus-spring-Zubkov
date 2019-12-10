package ru.otus.spring.homework14.storage;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework14.domain.Comment;

public interface CommentDao extends MongoRepository<Comment,String> {
}
