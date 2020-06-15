package ru.otus.project.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class History {

    private long id;
    private Vine vine;
    private double price;
    private LocalDate date;

    @Override
    public String toString() {
        return "цена = " + price +
                ", дата = " + date;
    }
}
