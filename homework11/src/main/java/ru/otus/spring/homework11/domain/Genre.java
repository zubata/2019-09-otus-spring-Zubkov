package ru.otus.spring.homework11.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "genre")
public class Genre {
    @Id
    private String id;

    @NonNull
    @Field("name")
    private String name;

    @Override
    public String toString() {
        return "id = " + id +
                ", жанр = '" + name + "'";
    }
}
