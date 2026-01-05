package com.akshay.testing.service;

import com.akshay.testing.entity.People;
import com.akshay.testing.repository.PeopleRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PeopleService {

    private final PeopleRepository repository;

    public PeopleService(PeopleRepository repository) {
        this.repository = repository;
    }

    public People addPeople(People people) {
        if (people == null || people.getName() == null) {
            throw new IllegalArgumentException("People or name must not be null");
        }
        return repository.save(people);
    }

    public People getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("People not found"));
    }
}
