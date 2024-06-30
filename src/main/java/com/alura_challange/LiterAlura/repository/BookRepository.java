package com.alura_challange.LiterAlura.repository;

import com.alura_challange.LiterAlura.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE :language MEMBER OF b.languages")
    List<Book> findByLanguage(String language);
}
