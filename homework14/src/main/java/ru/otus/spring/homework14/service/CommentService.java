package ru.otus.spring.homework14.service;

import ru.otus.spring.homework14.domain.Comment;
import ru.otus.spring.homework14.domain.CommentDto;

import java.util.List;

public interface CommentService {

    Comment convertToDomain(CommentDto commentDto);

}
