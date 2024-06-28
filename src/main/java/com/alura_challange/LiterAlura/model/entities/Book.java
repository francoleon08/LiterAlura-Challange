package com.alura_challange.LiterAlura.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class Book {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ElementCollection
    private List<String> languages;
    @ElementCollection
    private List<String> subjects;
    @OneToMany (cascade = CascadeType.ALL)
    private List<Person> authors;
    private int nro_downloads;

    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", languages=" + languages +
                ", subjects=" + subjects +
                ", authors=" + authors +
                ", nro_downloads=" + nro_downloads +
                '}';
    }
}
