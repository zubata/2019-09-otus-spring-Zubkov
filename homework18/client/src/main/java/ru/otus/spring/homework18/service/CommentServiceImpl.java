package ru.otus.spring.homework18.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.spring.homework18.domain.Book;
import ru.otus.spring.homework18.domain.Comment;
import ru.otus.spring.homework18.dto.CommentDto;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final RestTemplate restTemplate;

    @Value("${rest.fullUrl}")
    private String restUrl;

    @HystrixCommand(fallbackMethod = "insert_Fallback")
    @Override
    public Comment insert(CommentDto commentDto) {
        Comment temp = convertToDomain(commentDto);
        ResponseEntity<Comment> response = restTemplate.postForEntity(restUrl + "/api/comment/book", temp, Comment.class);
        return response.getBody();
    }

    @HystrixCommand(fallbackMethod = "showByBook_Fallback")
    @Override
    public List<Comment> showByBook(String bookname) {
        ResponseEntity<List<Comment>> response = restTemplate.exchange(restUrl + "/api/comment/book?bookname={bookname}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Comment>>() {
                }, bookname);
        return response.getBody();
    }

    @HystrixCommand(fallbackMethod = "delete_Fallback")
    @Override
    public String delete(long id) {
        restTemplate.delete(restUrl + "/api/comment/book?id={id}", id);
        return "Success deleting";
    }

    private Comment convertToDomain(CommentDto commentDto) {
        String bookname = commentDto.getBookname();
        ResponseEntity<Book> response = restTemplate.getForEntity(restUrl + "/api/book/name=" + bookname, Book.class);
        Comment temp = new Comment(response.getBody());
        temp.setComment(commentDto.getComment());
        return temp;
    }

    public Comment insert_Fallback(CommentDto commentDto) {
        return new Comment("Error with insert comment");
    }

    public List<Comment> showByBook_Fallback(String bookname) {
        return Collections.singletonList(new Comment("Error with show all comments by book"));
    }

    public String delete_Fallback(long id) {
        return "Error with delete comment";
    }
}
