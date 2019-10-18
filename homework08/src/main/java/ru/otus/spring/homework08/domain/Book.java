package ru.otus.spring.homework08.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "book")
public class Book {
    @Id
    private String id;

    @NonNull
    @Field("bookname")
    private String bookName;

    @NonNull
    @Field("author")
    private Author author;

    @NonNull
    @Field("genre")
    private Genre genre;

    @Override
    public String toString() {
        return "id = " + id +
                ", название = '" + bookName + '\'' +
                ", автор = " + author.getAuthorName() +
                ", жанр = " + genre.getGenreName();
    }
}
