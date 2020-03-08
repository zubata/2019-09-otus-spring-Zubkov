package ru.otus.project.domain;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Producer {

    private long id;

    @NonNull
    private String name;

    @Override
    public String toString() {
        return "id=" + id +
                ", название = " + name;
    }
}
