package ru.otus.spring.homework14.domain.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.otus.spring.homework14.domain.sql.BookSql;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class BookMongo {
    @Id
    private long id;

    @Field("name")
    private String name;

    @Field("author")
    private AuthorMongo author;

    @Field("genre")
    private List<GenreMongo> genre;

    public BookMongo(String name, AuthorMongo author, GenreMongo genre) {
        this.name = name;
        this.author = author;
        this.genre = Collections.singletonList(genre);
    }

    public void addGenre(GenreMongo genre) { this.genre.add(genre); }

    public void remove(GenreMongo genre) { this.genre.remove(genre); }

    public String getGenreList() {
        StringBuilder genresString = new StringBuilder();
        genre.forEach(genres -> genresString.append(genres.getName()).append(", "));
        return genresString.substring(0,genresString.length()-2);
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", название = '" + name + '\'' +
                ", автор = " + author.getName() +
                ", жанр = " + getGenreList();
    }

    public static BookMongo convetToDomain(BookSql bookSql) {
        long id = bookSql.getId();
        String name = bookSql.getName();
        AuthorMongo tempAuthor = AuthorMongo.convertToDomain(bookSql.getAuthor());
        List<GenreMongo> list = bookSql.getGenre().stream().map(GenreMongo::convertToDomain).collect(Collectors.toList());
        return new BookMongo(id,name,tempAuthor,list);
    }
}
