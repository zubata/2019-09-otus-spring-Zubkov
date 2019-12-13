package ru.otus.spring.homework14.domain.mongo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.otus.spring.homework14.domain.sql.CommentSql;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comments")
public class CommentMongo {
    @Id
    private long id;

    @NonNull
    @Field("book")
    private BookMongo book;

    @Field("comment")
    private String comment;

    @Override
    public String toString() {
        return "id = " + id +
                ", книга = " + book.getName() +
                ", комментарий = '" + comment + "'";
    }

    public static CommentMongo convertToDomain(CommentSql commentSql) {
        long id = commentSql.getId();
        BookMongo book = BookMongo.convetToDomain(commentSql.getBook());
        String comment = commentSql.getComment();
        return new CommentMongo(id,book,comment);
    }
}
