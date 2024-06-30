package com.alura_challange.LiterAlura.model;

import com.alura_challange.LiterAlura.model.event.EventSearchBookManager;
import com.alura_challange.LiterAlura.model.event.ListenerModelSearchBook;

public abstract class Model {
    protected EventSearchBookManager eventSearchBookManager;

    public Model() {
        this.eventSearchBookManager = new EventSearchBookManager();
    }

    public void addListener(ListenerModelSearchBook listener) {
        eventSearchBookManager.addListener(listener);
    }

    public void removeListener(ListenerModelSearchBook listener) {
        eventSearchBookManager.removeListener(listener);
    }
}
