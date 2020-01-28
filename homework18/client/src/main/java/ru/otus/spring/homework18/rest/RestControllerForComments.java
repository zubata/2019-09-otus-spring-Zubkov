package ru.otus.spring.homework18.rest;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework18.domain.Comment;
import ru.otus.spring.homework18.dto.CommentDto;
import ru.otus.spring.homework18.service.CommentService;

import java.util.List;

@Data
@RestController
public class RestControllerForComments {
    private final CommentService commentService;

    @GetMapping("/api/comment/book")
    public ResponseEntity<List<Comment>> getCommentByBook(@RequestParam("bookname") String bookname) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.showByBook(bookname));
    }

    @PostMapping("/api/comment/book")
    public @ResponseBody
    ResponseEntity<Comment> insertComment(@RequestBody CommentDto commentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.insert(commentDto));
    }


    @DeleteMapping("/api/comment/book")
    public ResponseEntity deleteCommentById(@RequestBody Comment comment) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.delete(comment.getId()));
    }
}
