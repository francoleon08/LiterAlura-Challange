package com.alura_challange.LiterAlura.view;

import com.alura_challange.LiterAlura.controller.ControllerImpl;
import com.alura_challange.LiterAlura.model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Component
public class ViewCommandLineInterface {
    @Autowired
    private ControllerImpl controllerImpl;
    @Autowired
    private ModelRetrieveAllAuthors modelRetrieveAllAuthors;
    @Autowired
    private ModelRetrieveAllAuthorsAliveInYear modelRetrieveAllAuthorsAliveInYear;
    @Autowired
    private ModelRetrieveAllBooks modelRetrieveAllBooks;
    @Autowired
    private ModelRetrieveBooksByLanguage modelRetrieveBooksByLanguage;
    @Autowired
    private ModelSearchBookByTitle modelSearchBookByTitle;

    private final Scanner scanner;

    public ViewCommandLineInterface() {
        scanner = new Scanner(System.in);
    }

    @PostConstruct
    private void initListeners() {
        Objects.requireNonNull(modelRetrieveAllAuthors).addListener(this::showAllAuthors);
        Objects.requireNonNull(modelRetrieveAllAuthorsAliveInYear).addListener(this::showAllAuthorsAliveInYear);
        Objects.requireNonNull(modelRetrieveAllBooks).addListener(this::showAllBoks);
        Objects.requireNonNull(modelRetrieveBooksByLanguage).addListener(this::showAllBoksByLanguage);
        Objects.requireNonNull(modelSearchBookByTitle).addListener(this::showSearchBookByTitle);
    }

    private void showAllAuthors() {
        System.out.println("Autores registrados:");
        showEntities(modelRetrieveAllAuthors.getAllAuthors());
    }

    private void showAllAuthorsAliveInYear() {
        System.out.println("Autores vivos en determinado año:");
        showEntities(modelRetrieveAllAuthorsAliveInYear.getAllAuthors());
    }

    private void showAllBoks() {
        System.out.println("Libros registrados:");
        showEntities(modelRetrieveAllBooks.getAllBooks());
    }

    private void showAllBoksByLanguage() {
        System.out.println("Libros en determinado idioma:");
        showEntities(modelRetrieveBooksByLanguage.getAllBooks());
    }

    private <E> void showEntities(List<E> entities) {
        for (E entity : entities) {
            System.out.println(entity);
        }
    }

    private void showSearchBookByTitle() {
        System.out.println("Resultado de la búsqueda:");
        System.out.println(modelSearchBookByTitle.getLastBook());
        System.out.println("Libro almacenado...");
    }

    @PostConstruct
    public void showMenu() {
        int option = 0;
        while (option != 6) {
            showOptions();
            option = getIntegerInputOption();
            switch (option) {
                case 1:
                    handleSearchBookByTitle();
                    break;
                case 2:
                    handleRetrieveAllBooks();
                    break;
                case 3:
                    handleRetrieveAllAuthors();
                    break;
                case 4:
                    handleAuthorsAliveInYear();
                    break;
                case 5:
                    handleBooksForLanguage();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    private void showOptions() {
        System.out.println("1. Buscar libro por titulo.");
        System.out.println("2. Listar libros registrados.");
        System.out.println("3. Listar autores registrados.");
        System.out.println("4. Listar autores vivos en un determinado año.");
        System.out.println("5. Listar libros por idioma.");
        System.out.println("6. Salir.");
    }

    private int getIntegerInputOption() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Opción inválida.");
            scanner.next();
            return getIntegerInputOption();
        }
    }

    private void handleSearchBookByTitle() {
        System.out.println("Ingrese el título del libro a buscar:");
        controllerImpl.searchBookByTitle(scanner.next());
    }

    private void handleRetrieveAllBooks() {
        controllerImpl.listBooks();
    }

    private void handleRetrieveAllAuthors() {
        controllerImpl.listAuthors();
    }

    private void handleAuthorsAliveInYear() {
        System.out.println("Ingrese el año a buscar:");
        controllerImpl.listAuthorsAliveInYear(getIntegerInputOption());
    }

    private void handleBooksForLanguage() {
        System.out.println("Ingrese el idioma a buscar:");
        controllerImpl.listBooksByLanguage(scanner.next());
    }
}
