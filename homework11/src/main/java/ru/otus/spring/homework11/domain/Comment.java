package ru.otus.spring.homework11.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
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
                ", книга = " + book.getName() +
                ", комментарий = '" + comment + "'";
    }
}
