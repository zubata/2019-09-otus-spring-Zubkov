package ru.otus.spring.homework08.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
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
    private List<Genre> genreList;

    public Book(String name, Author author, Genre... genre) {
        this.name = name;
        this.author = author;
        this.genreList = Arrays.asList(genre);
    }

    public Book(String id, String name, Author author, Genre... genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genreList = Arrays.asList(genre);
    }

    @Override
    public String toString() {
        String genresString = genreList.stream().map(temp -> temp.toString() + ", ").collect(Collectors.joining());
        return "id = " + id +
                ", название = '" + name + '\'' +
                ", автор = " + author.getAuthorName() +
                ", жанр/жанры = " + genresString.substring(0,genresString.length()-2);
    }

    public void addGenre(Genre genre) { genreList.add(genre); }

    public void delete(Genre genre) {
        genreList.remove(genre);
    }
}
