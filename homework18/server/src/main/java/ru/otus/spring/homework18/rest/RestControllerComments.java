package ru.otus.spring.homework18.rest;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework18.domain.Comment;
import ru.otus.spring.homework18.storage.CommentDao;

import java.util.List;

@Data
@RestController
public class RestControllerComments {
    private final CommentDao commentService;

    @GetMapping("/api/comment/book")
    public ResponseEntity<List<Comment>> getCommentByBook(@RequestParam String bookname) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getByBookName(bookname));
    }

    @PostMapping("/api/comment/book")
    public @ResponseBody
    ResponseEntity<Comment> insertComment(@RequestBody Comment comment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(comment));
    }


    @DeleteMapping("/api/comment/book")
    public ResponseEntity deleteCommentById(@RequestParam String id) {
        commentService.deleteById(Long.parseLong(id));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
