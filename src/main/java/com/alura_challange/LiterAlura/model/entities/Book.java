package com.alura_challange.LiterAlura.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> languages;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> subjects;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Person> authors;
    private int nro_downloads;

    public String toString() {
        return "Libro: \n" +
                "Titulo = " + title + "\n" +
                "Autores = " + authors + "\n" +
                "Idiomas = " + languages + "\n" +
                "Temas = " + subjects + "\n" +
                "Número de descargas = " + nro_downloads + "\n";
    }
}
