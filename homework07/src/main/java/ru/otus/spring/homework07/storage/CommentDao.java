package ru.otus.spring.homework07.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.spring.homework07.domain.Comment;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment,Long> {

    Comment getById(long id);

    @Query("select c from Comment c where c.book.bookName = ?1")
    List getByBook(String bookname);

}
