package com.alura_challange.LiterAlura.repository;

import com.alura_challange.LiterAlura.model.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

    @Query("SELECT p FROM Person p WHERE p.death_year IS NULL OR p.death_year >= :year")
    List<Person> findByDeathYearIsNullOrDeathYearGreaterThanEqual(@Param("year") int year);
}
