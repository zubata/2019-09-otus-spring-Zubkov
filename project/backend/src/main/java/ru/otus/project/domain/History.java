package ru.otus.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = Vine.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "vine_id")
    @JsonIgnoreProperties(value = {"historyPrice", "producer", "hibernateLazyInitializer", "handler"})
    private Vine vine;

    private double price;

    private LocalDate date;

    public History(Vine vine, double price) {
        this.vine = vine;
        this.price = price;
        this.date = LocalDate.now();
    }
}
