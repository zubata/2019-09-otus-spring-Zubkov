package ru.otus.spring.homework06.storage;

import ru.otus.spring.homework06.domain.Comment;

import java.util.List;

public interface CommentDao {

    void insert(Comment comment);

    long count();

    Comment getById(long id);

    List getByBook(String bookname);

    List getAll();

    void delete(long id);
}
