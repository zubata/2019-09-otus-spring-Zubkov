package ru.otus.spring.homework18.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework18.domain.Comment;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment,Long> {

    Comment getById(long id);

    //@Query("select c from Comment c where c.book.bookName = ?1")
    List<Comment> getByBookName(String bookname);

   //@Override
   //Comment save(Comment comment);
}
