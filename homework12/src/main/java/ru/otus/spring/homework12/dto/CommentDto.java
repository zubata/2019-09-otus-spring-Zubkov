package ru.otus.spring.homework12.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private String id;
    private String bookname;
    private String comment;

    public CommentDto(String bookname) {
        this.bookname = bookname;
    }
}
