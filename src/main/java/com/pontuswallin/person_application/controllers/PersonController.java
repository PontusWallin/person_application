package com.pontuswallin.person_application.controllers;

import com.pontuswallin.person_application.dto.PersonDTO;
import com.pontuswallin.person_application.services.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }
    @GetMapping("/person")
    public List<PersonDTO> searchPerson(@RequestParam("search") String search) {
        return service.searchPerson(search);
    }

    @PostMapping("/person")
    public PersonDTO addPerson(@RequestBody PersonDTO person) {
        return service.addPerson(person);
    }
}
