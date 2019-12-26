package ru.otus.spring.homework15.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Payload;
import ru.otus.spring.homework15.domain.Author;

import java.util.List;

@MessagingGateway
public interface GatewayForAuthor {

    @Payload("new java.util.Date()")
    @Gateway(requestChannel = "authorFlowForList.input")
    List<Author> forListAuthor();

    @Gateway(requestChannel = "authorFlowForFind.input")
    Author forFindAuthor(long id);

    @Gateway(requestChannel = "authorFlowForInsert.input")
    Author forInsertAuthor(Author author);

    @Gateway(requestChannel = "authorFlowForDelete.input")
    String forDeleteAuthor(long id);

}
