package ru.otus.spring.homework15.service;

import ru.otus.spring.homework15.domain.Comment;
import ru.otus.spring.homework15.dto.CommentDto;

import java.util.List;

public interface CommentService {

    Comment insert(Comment comment);

    List<Comment> showByBook(String bookname);

    String deleteById(long id);

    Comment convertToDomain(CommentDto commentDto);

}
