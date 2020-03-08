package ru.otus.spring.homework18.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private String bookname;
    private String comment;

    public CommentDto(String bookname) {
        this.bookname = bookname;
    }
}
