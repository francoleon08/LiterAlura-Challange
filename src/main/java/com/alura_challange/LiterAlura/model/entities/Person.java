package com.alura_challange.LiterAlura.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
@Entity
public class Person {
    @Id
    private String name;
    private int birth_year;
    private int death_year;

    public String toString() {
        return name;
    }
}
