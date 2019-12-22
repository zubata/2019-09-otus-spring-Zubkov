package ru.otus.spring.homework15.controllers;

import lombok.Data;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.homework15.domain.Comment;
import ru.otus.spring.homework15.dto.CommentDto;
import ru.otus.spring.homework15.integration.GatewayForComment;
import ru.otus.spring.homework15.security.MyAclService;
import ru.otus.spring.homework15.service.CommentService;

import java.util.List;

@Data
@Controller
public class ControllerForComment {
    private final GatewayForComment gateway;
    private final MyAclService myAclService;

    @GetMapping("/comment/book")
    public String getCommentByBook(@RequestParam("bookname") String bookname, Model model) {
        List<Comment> comments = gateway.forListByBook(bookname);
        model.addAttribute("comment", comments);
        model.addAttribute("bookname", bookname);
        model.addAttribute("adminmode", turnAdminMode());
        return "commentList";
    }

    @PostMapping("/comment/add")
    public String insertComment(CommentDto commentDto, Model model) {
        gateway.forInsertComment(commentDto);
        model.addAttribute("bookname", commentDto.getBookname());
        return "resultInsertComment";
    }


    @GetMapping("/comment/insertcomment")
    public String getPageInsert(@RequestParam("bookname") String bookname, Model model) {
        CommentDto form = new CommentDto(bookname);
        model.addAttribute("comment", form);
        return "insertComment";
    }


    @PostMapping("/comment/del")
    public String deleteCommentById(CommentDto commentDto, Model model) {
        String result = gateway.forDeleteComment(Long.parseLong(commentDto.getId()));
        model.addAttribute("result", result);
        model.addAttribute("bookname", commentDto.getBookname());
        return "deleteComment";
    }

    private boolean turnAdminMode() {
        try {
            return myAclService.isAdmin();
        } catch (AccessDeniedException e) {
            return false;
        }
    }
}
