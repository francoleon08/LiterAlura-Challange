package com.alura_challange.LiterAlura.model;

import com.alura_challange.LiterAlura.model.GutendexAPI.GutendexAPIClient;
import com.alura_challange.LiterAlura.model.entities.Book;
import com.alura_challange.LiterAlura.model.exceptions.BookSearchException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelSearchBookByTitle extends Model {
    @Setter
    @Autowired
    private GutendexAPIClient client;
    @Getter
    private Book lastBook;

    public ModelSearchBookByTitle() {
        super();
    }

    public void searchBookByTitle(String title) throws BookSearchException {
        try {
            lastBook = client.getBookByTitle(title);
            notifyListeners();
        } catch (Exception e) {
            throw new BookSearchException("Error while searching book by title");
        }
    }
}
