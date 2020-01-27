package ru.otus.spring.homework18.domain;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Genre {
    private long id;
    @NonNull
    private String name;

    @Override
    public String toString() {
        return "id = " + id +
                ", жанр = '" + name + "'";
    }
}
