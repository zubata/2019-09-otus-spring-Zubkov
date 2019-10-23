package ru.otus.spring.homework08.storage;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework08.domain.Comment;

import java.util.List;

public interface CommentDao extends MongoRepository<Comment, String> {

    Comment getById(String id);

    List<Comment> getByBookName(String name);

    List<Comment> getByBookId(String id);

    long countByBookId(String bookId);
}
