package com.akshay.testing.controller;

import com.akshay.testing.entity.People;
import com.akshay.testing.service.PeopleService;
import org.apache.el.util.ReflectionUtil;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService service;

    public PeopleController(PeopleService service) {


        this.service = service;
    }

    @PostMapping
    public People create(@RequestBody People people) {
        return service.addPeople(people);
    }

    @GetMapping("/{id}")
    public People get(@PathVariable UUID id) {
        return service.getById(id);
    }
}
