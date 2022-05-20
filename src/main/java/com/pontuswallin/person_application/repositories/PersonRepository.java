package com.pontuswallin.person_application.repositories;

import com.pontuswallin.person_application.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Optional<List<Person>> findPersonByNameContainingIgnoreCase(
            @Param("search") String search
    );
}