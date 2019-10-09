package ru.otus.spring.homework07.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.homework07.domain.Comment;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment,Long> {

    Comment getById(long id);

    List getByBook(String bookname);

}
