package com.alura_challange.LiterAlura.model.event;

import java.util.ArrayList;
import java.util.List;

public class EventSearchBookManager {
    private final List<ListenerModelSearchBook> listeners;

    public EventSearchBookManager() {
        listeners = new ArrayList<>();
    }

    public void addListener(ListenerModelSearchBook listener) {
        listeners.add(listener);
    }

    public void removeListener(ListenerModelSearchBook listener) {
        listeners.remove(listener);
    }

    public void notifyListeners() {
        for (ListenerModelSearchBook listener : listeners) {
            listener.hasFinishedSearchBook();
        }
    }
}
