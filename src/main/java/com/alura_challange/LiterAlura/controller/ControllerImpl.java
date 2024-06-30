package com.alura_challange.LiterAlura.controller;

import com.alura_challange.LiterAlura.model.*;
import com.alura_challange.LiterAlura.model.entities.Book;
import com.alura_challange.LiterAlura.model.exceptions.BookSearchException;
import com.alura_challange.LiterAlura.repository.BookRepository;
import com.alura_challange.LiterAlura.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ControllerImpl implements Controller {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ModelSearchBookByTitle modelSearchBookByTitle;
    @Autowired
    private ModelRetrieveAllBooks modelRetrieveAllBooks;
    @Autowired
    private ModelRetrieveAllAuthors modelRetrieveAllAuthors;
    @Autowired
    private ModelRetrieveBooksByLanguage modelRetrieveBooksByLanguage;
    @Autowired
    private ModelRetrieveAllAuthorsAliveInYear modelRetrieveAllAuthorsAliveInYear;

    @Override
    public void searchBookByTitle(String title) {
        try {
            modelSearchBookByTitle.searchBookByTitle(title);
            Book book = modelSearchBookByTitle.getLastBook();
            bookRepository.save(book);
        } catch (BookSearchException | DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void listBooks() {
        modelRetrieveAllBooks.retrieveAllBooks();
    }

    @Override
    public void listAuthors() {
        modelRetrieveAllAuthors.retrieveAllAuthors();
    }

    @Override
    public void listAuthorsAliveInYear(int year) {
        modelRetrieveAllAuthorsAliveInYear.retrieveAllAuthorsAliveInYear(year);
    }

    @Override
    public void listBooksByLanguage(String language) {
        modelRetrieveBooksByLanguage.retrieveBooksByLanguage(language);
    }
}
