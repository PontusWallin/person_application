package com.pontuswallin.person_application.services;

import com.pontuswallin.person_application.dto.PersonDTO;
import com.pontuswallin.person_application.model.Person;
import com.pontuswallin.person_application.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    private PersonService personService;

    private static final String SHOULD_NOT_BE_FOUND = "shouldnotbe foundbysearch";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personService = new PersonService(new ModelMapper(), personRepository);
    }

    @Test
    void searchPersonShouldReturnList() {
        var testSearchString = "Test";
        var testPersonList = createTestPersonList();
        var expected = createExpectedPersonDTOList();
        when(personRepository.findPersonByNameContainingIgnoreCase(testSearchString))
                .thenReturn(Optional.of(testPersonList));
        var actual = personService.searchPerson(testSearchString);

        assertEquals(actual.size(), expected.size());
        assertEquals(actual.get(0).getName(), expected.get(0).getName());
        assertEquals(actual.get(1).getName(), expected.get(1).getName());
        assertEquals(actual.get(2).getName(), expected.get(2).getName());
    }

    @Test
    void searchPersonShouldReturnEmptyList() {
        when(personRepository.findPersonByNameContainingIgnoreCase("nothing should be found"))
                .thenReturn(Optional.of(new ArrayList<>()));
        var actual = personService.searchPerson("nothing should be found");

        assertEquals(actual.size(), 0);
    }

    @Test
    void addPersonShouldWorkWithCorrectInput() {
        var testPerson = createTestPerson();
        var testPersonDTO = new PersonDTO(testPerson.getName());
        var expected = testPersonDTO;

        when(personRepository.save(any())).thenReturn(testPerson);
        var actual = personService.addPerson(testPersonDTO);

        assertEquals(actual, expected);
    }

    private Person createTestPerson() {
        return new Person(123L, "First testperson");
    }

    private List<Person> createTestPersonList() {
        List<Person> personList = new ArrayList<>();
        personList.add(createTestPerson());

        var testPerson2 = new Person(123L, "Second testperson");
        personList.add(testPerson2);

        var testPerson3 = new Person(123L, "Third testperson");
        personList.add(testPerson3);

        return personList;
    }

    private List<PersonDTO> createExpectedPersonDTOList() {
        List<PersonDTO> personDTOList = new ArrayList<>();

        var testPersonDTO = new PersonDTO("First testperson");
        personDTOList.add(testPersonDTO);

        var testPersonDTO2 = new PersonDTO("Second testperson");
        personDTOList.add(testPersonDTO2);

        var testPersonDTO3 = new PersonDTO("Third testperson");
        personDTOList.add(testPersonDTO3);

        return personDTOList;
    }
}