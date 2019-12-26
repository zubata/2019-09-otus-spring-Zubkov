package ru.otus.spring.homework15.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.homework15.domain.Comment;
import ru.otus.spring.homework15.dto.BookDto;
import ru.otus.spring.homework15.dto.CommentDto;

import java.util.List;

@MessagingGateway
public interface GatewayForComment {

    @Gateway(requestChannel = "commentFlowForListByBook.input")
    List<Comment> forListByBook(String bookname);

    @Gateway(requestChannel = "commentFlowForInsert.input")
    Comment forInsertComment(CommentDto commentDto);

    @Gateway(requestChannel = "commentFlowForDelete.input")
    String forDeleteComment(long id);
}
