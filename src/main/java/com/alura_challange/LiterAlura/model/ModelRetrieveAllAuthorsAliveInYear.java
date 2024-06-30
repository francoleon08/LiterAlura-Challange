package com.alura_challange.LiterAlura.model;

import com.alura_challange.LiterAlura.model.entities.Person;
import com.alura_challange.LiterAlura.repository.PersonRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelRetrieveAllAuthorsAliveInYear extends Model {
    @Autowired
    private PersonRepository personRepository;
    @Getter
    private List<Person> allAuthors;

    public void retrieveAllAuthorsAliveInYear(int year) {
        allAuthors = personRepository.findByDeathYearIsNullOrDeathYearGreaterThanEqual(year);
        notifyListeners();
    }
}
