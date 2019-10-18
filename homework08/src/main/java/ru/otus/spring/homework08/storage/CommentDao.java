package ru.otus.spring.homework08.storage;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.otus.spring.homework08.domain.Comment;

import java.util.List;

public interface CommentDao extends MongoRepository<Comment, String> {

    Comment getById(String id);

    @Query("{'book.bookname': ?0 }")
    List<Comment> getByBookName(String bookname);

    @Query("{'book._id': ?0 }")
    List<Comment> getByBookId(String id);

}
