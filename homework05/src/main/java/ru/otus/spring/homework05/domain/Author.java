package ru.otus.spring.homework05.domain;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Author {

    private long id;

    @NonNull
    private String authorName;

}
