package ru.otus.project.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "persons")
@NamedEntityGraph(name = "PersonAndVine",
        attributeNodes = {@NamedAttributeNode(value = "vine")})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String role;
    private String email;

    @ManyToMany(targetEntity = Vine.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "favourite_vine")
    @JoinColumn(name = "id")
    private List<Vine> vine;

    public Person() {
        this.vine = new ArrayList<Vine>();
    }

    public String getVineList() {
        StringBuilder favoriteString = new StringBuilder();
        vine.forEach(genres -> favoriteString.append(genres.getName()).append(", "));
        return favoriteString.substring(0, favoriteString.length() - 2);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", favoriteVines=" + getVine() +
                '}';
    }
}
