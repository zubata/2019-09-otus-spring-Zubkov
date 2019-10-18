package ru.otus.spring.homework08.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comment")
public class Comment {
    @Id
    private String id;

    @NonNull
    @Field("book")
    private Book book;

    @Field("comment")
    private String comment;

    @Override
    public String toString() {
        return "id = " + id +
                ", книга = '" + book.getBookName() +
                "', комментарий = '" + comment + "'";
    }
}
