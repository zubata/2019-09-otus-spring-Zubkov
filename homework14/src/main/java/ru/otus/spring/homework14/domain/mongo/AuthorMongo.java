package ru.otus.spring.homework14.domain.mongo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.otus.spring.homework14.domain.sql.AuthorSql;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "authors")
public class AuthorMongo {
    @Id
    private long id;

    @NonNull
    @Field("name")
    private String name;

    @Override
    public String toString() {
        return "id =" + id +
                ", автор = '" + name + "'";
    }

    public static AuthorMongo convertToDomain(AuthorSql authorSql) {
        long id = authorSql.getId();
        String name = authorSql.getName();
        return new AuthorMongo(id,name);
    }
}
