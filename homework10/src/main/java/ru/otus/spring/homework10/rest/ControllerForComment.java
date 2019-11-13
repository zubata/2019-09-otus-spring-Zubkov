package ru.otus.spring.homework10.rest;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework10.domain.Comment;
import ru.otus.spring.homework10.dto.CommentDto;
import ru.otus.spring.homework10.service.CommentService;

import java.util.List;

@Data
@RestController
public class ControllerForComment {
    private final CommentService commentService;

    @GetMapping("/api/comment/book")
    public List<Comment> getCommentByBook(@RequestParam("bookname") String bookname) {
        return commentService.showByBook(bookname);
    }

    @PostMapping("/api/comment/book")
    public @ResponseBody
    ResponseEntity<Comment> insertComment(@RequestBody CommentDto commentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.insert(commentDto));
    }


    @DeleteMapping("/api/comment/book")
    public void deleteCommentById(@RequestBody Comment comment) {
        commentService.deleteById(comment.getId());
    }
}
