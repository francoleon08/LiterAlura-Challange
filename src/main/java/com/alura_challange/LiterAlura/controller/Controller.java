package com.alura_challange.LiterAlura.controller;

public interface Controller {
    void searchBookByTitle(String title);

    void listBooks();

    void listAuthors();

    void listAuthorsAliveInYear(int year);

    void listBooksByLanguage(String language);
}
