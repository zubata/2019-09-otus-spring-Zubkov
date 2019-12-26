package ru.otus.spring.homework15.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.access.prepost.PostFilter;
import ru.otus.spring.homework15.domain.Book;
import ru.otus.spring.homework15.dto.BookDto;

import java.util.List;

@MessagingGateway
public interface GatewayForBook {

    @Payload("new java.util.Date()")
    @Gateway(requestChannel = "bookFlowForList.input")
    @PostFilter("hasPermission(filterObject,'READ')")
    List<Book> forListBook();

    @Gateway(requestChannel = "bookFlowForFind.input")
    Book forFindBook(long id);

    @Gateway(requestChannel = "bookFlowForInsert.input")
    Book forInsertBook(BookDto bookDto);

    @Gateway(requestChannel = "bookFlowForDelete.input")
    String forDeleteBook(long id);
}
