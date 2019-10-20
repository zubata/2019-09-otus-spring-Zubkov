package ru.otus.spring.homework08.domain;

import lombok.*;

@Data
@AllArgsConstructor
public class Genre {

    private String name;

    @Override
    public String toString() {
        return name;
    }
}
