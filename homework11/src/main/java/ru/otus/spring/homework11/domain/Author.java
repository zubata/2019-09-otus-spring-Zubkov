package ru.otus.spring.homework11.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Document(collection = "author")
@NoArgsConstructor
public class Author {
    @Id
    private String id;

    @NonNull
    @JsonProperty("name")
    @Field("name")
    private String name;

    @Override
    public String toString() {
        return "id =" + id +
                ", автор = '" + name + "'";
    }
}
