package ru.otus.spring.homework18.service;

import ru.otus.spring.homework18.domain.Comment;
import ru.otus.spring.homework18.dto.CommentDto;

import java.util.List;

public interface CommentService {

    Comment insert(CommentDto commentDto);

    List<Comment> showByBook(String bookname);

    String delete(long id);

}
