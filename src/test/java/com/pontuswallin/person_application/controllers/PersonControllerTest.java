package com.pontuswallin.person_application.controllers;

import com.pontuswallin.person_application.dto.PersonDTO;
import com.pontuswallin.person_application.model.Person;
import com.pontuswallin.person_application.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonControllerTest {

    @Mock
    private PersonService personService;

    private PersonController personController;

    private List<PersonDTO> personDTOS;

    private PersonDTO personDTO;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        personController = new PersonController(personService);

        personDTOS = createTestPersonDTOList();
        personDTO = createTestPersonDTO();
    }

    @Test
    void searchPerson() {
        String testSearchString = "test";
        when(personService.searchPerson(testSearchString)).thenReturn(personDTOS);
        var actual = personController.searchPerson(testSearchString);

        verify(personService, times(1)).searchPerson(testSearchString);
        assertEquals(actual.size(), personDTOS.size());
        assertEquals(actual.get(0).getName(), personDTOS.get(0).getName());
        assertEquals(actual.get(1).getName(), personDTOS.get(1).getName());
    }

    @Test
    void addPerson() {
        when(personService.addPerson(personDTO)).thenReturn(personDTO);
        var actual = personController.addPerson(personDTO);

        verify(personService, times(1)).addPerson(personDTO);
        assertEquals(actual, personDTO);
    }

    private List<PersonDTO> createTestPersonDTOList() {
        List<PersonDTO> testList = new ArrayList<>();
        PersonDTO testPersonDTO = createTestPersonDTO();
        testList.add(testPersonDTO);

        PersonDTO testPersonDTO2 = new PersonDTO("Second Testperson");
        testList.add(testPersonDTO2);
        return testList;
    }

    private PersonDTO createTestPersonDTO() {
        PersonDTO testPersonDTO = new PersonDTO("First Testperson");
        return testPersonDTO;
    }

    private List<Person> createTestPersonList() {
        List<Person> testList = new ArrayList<>();
        Person testPerson = createTestPerson();
        testList.add(testPerson);

        Person testPerson2 = new Person(123L, "Second Testperson");
        testList.add(testPerson2);

        return testList;
    }

    private Person createTestPerson() {
        return new Person(123L, "First Testperson");
    }
}