package com.alura_challange.LiterAlura.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Person {
    @Id
    private String name;
    private int birth_year;
    private Integer death_year;

    public String toString() {
        return name;
    }
}
