package ru.otus.spring.homework10.rest;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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

    @PostMapping("/api/comment/add")
    public @ResponseBody
    ResponseEntity<String> insertComment(@RequestBody CommentDto commentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.insert(commentDto));
    }


    @PostMapping("/api/comment/del")
    public @ResponseBody
    ResponseEntity<String> deleteCommentById(@RequestBody Comment comment) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.deleteById(comment.getId()));
    }
}
