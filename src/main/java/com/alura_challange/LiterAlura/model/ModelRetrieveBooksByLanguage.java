package com.alura_challange.LiterAlura.model;

import com.alura_challange.LiterAlura.model.entities.Book;
import com.alura_challange.LiterAlura.repository.BookRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelRetrieveBooksByLanguage extends Model {
    @Autowired
    private BookRepository bookRepository;
    @Getter
    private List<Book> allBooks;

    public void retrieveBooksByLanguage(String language) {
        allBooks = bookRepository.findByLanguage(language);
    }
}
