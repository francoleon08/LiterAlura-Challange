package com.alura_challange.LiterAlura.model;

import com.alura_challange.LiterAlura.model.GutendexAPI.GutendexAPIClient;
import com.alura_challange.LiterAlura.model.entities.Book;
import com.alura_challange.LiterAlura.model.event.EventSearchBookManager;
import com.alura_challange.LiterAlura.model.event.ListenerModelSearchBook;
import com.alura_challange.LiterAlura.model.exceptions.BookSearchException;
import lombok.Getter;
import lombok.Setter;

public class ModelSearchBookByTitle {
    private EventSearchBookManager eventSearchBookManager;
    @Setter
    private GutendexAPIClient client;
    @Getter
    private Book lastBook;

    public ModelSearchBookByTitle(GutendexAPIClient client) {
        this.client = client;
        this.eventSearchBookManager = new EventSearchBookManager();
    }

    public void addListener(ListenerModelSearchBook listener) {
        eventSearchBookManager.addListener(listener);
    }

    public void removeListener(ListenerModelSearchBook listener) {
        eventSearchBookManager.removeListener(listener);
    }

    public void searchBookByTitle(String title) throws BookSearchException {
        try {
            lastBook = client.getBookByTitle(title);
            eventSearchBookManager.notifyListeners();
        } catch (Exception e) {
            throw new BookSearchException("Error while searching book by title");
        }
    }
}
