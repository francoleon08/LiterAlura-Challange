package com.alura_challange.LiterAlura.controller;

public interface Controller {
    public void searchBookByTitle(String title);
    public void listBooks();
    public void listAuthors();
    public void listAuthorsAliveInYear(int year);
    public void listBooksByLanguage(String language);
}
