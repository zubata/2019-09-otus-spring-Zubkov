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
    @Field("name")
    private String name;

    @NonNull
    @Field("author")
    private Author author;

    @Field("genre")
    private Genre genre;

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", название = '" + name + '\'' +
                ", автор = " + author.getAuthorName() +
                ", жанр = " + genre;
    }
}
