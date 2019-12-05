package ru.otus.spring.homework13.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework13.domain.Comment;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment,Long> {

    Comment getById(long id);

    //@Query("select c from Comment c where c.book.bookName = ?1")
    List<Comment> getByBookName(String bookname);

    long countByBookId(long bookId);

}
