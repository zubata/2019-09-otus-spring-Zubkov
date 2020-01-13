package ru.otus.spring.homework17.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.homework17.domain.Comment;

import java.util.List;

@Repository
public interface RestControllerForComment extends PagingAndSortingRepository<Comment, Long> {

    @RestResource(path = "commentsbook", rel = "commentsbybook")
    List<Comment> findByBookName(@RequestParam("bookname") String bookname);

    //@RestResource(path = "commentsbook", rel = "commentsbybook")
    //List<Comment> findByBookName(@RequestBody String bookname);
}
