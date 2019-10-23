package ru.otus.spring.homework08.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "author")
public class Author {
    @Id
    private String id;

    @NonNull
    @Field("authorname")
    private String authorName;

    @Override
    public String toString() {
        return "id = " + id +
                ", автор = '" + authorName + "'";
    }
}
