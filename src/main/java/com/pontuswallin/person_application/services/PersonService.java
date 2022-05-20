package com.pontuswallin.person_application.services;

import com.pontuswallin.person_application.dto.PersonDTO;
import com.pontuswallin.person_application.model.Person;
import com.pontuswallin.person_application.repositories.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final ModelMapper mapper;

    private final PersonRepository repository;

    public PersonService(ModelMapper mapper, PersonRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<PersonDTO> searchPerson(String search) {
        var personsOptional =
                repository.findPersonByNameContainingIgnoreCase(search);

        if(personsOptional.isEmpty()) {
            return new ArrayList<>();
        }

        return personsOptional.get().stream()
                .map(person -> mapper.map(person, PersonDTO.class))
                .collect(Collectors.toList());
    }

    public PersonDTO addPerson(PersonDTO personDTO) {
        var person = mapper.map(personDTO, Person.class);
        person = repository.save(person);
        return mapper.map(person, PersonDTO.class);
    }
}
