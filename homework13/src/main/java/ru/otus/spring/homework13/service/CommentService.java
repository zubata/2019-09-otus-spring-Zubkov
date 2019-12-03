package ru.otus.spring.homework13.service;

import ru.otus.spring.homework13.domain.Comment;
import ru.otus.spring.homework13.dto.CommentDto;

import java.util.List;

public interface CommentService {

    String insert(CommentDto commentDto);

    List<Comment> showByBook(String bookname);

    String deleteById(long id);

}
