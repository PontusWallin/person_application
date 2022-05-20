package com.pontuswallin.person_application.controllers;

import com.pontuswallin.person_application.dto.PersonDTO;
import com.pontuswallin.person_application.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
class PersonControllerMockMVCTest {

    @MockBean
    PersonService personService;

    @Autowired
    MockMvc mvc;

    @InjectMocks
    PersonController personController;

    List<PersonDTO> personDTOList;

    @BeforeEach
    void setUp() {

        personDTOList = new ArrayList<>();
        personDTOList.add(PersonDTO.builder().name("First testperson").build());
        personDTOList.add(PersonDTO.builder().name("Second testperson").build());
    }

    @Test
    void searchPersonShouldReturnList() throws Exception {
        when(personService.searchPerson("Test")).thenReturn(personDTOList);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("search","Test");

        mvc.perform(MockMvcRequestBuilders
                        .get("/person")
                        .params(params)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0]").value(personDTOList.get(0)))
                .andExpect(jsonPath("$.[1]").value(personDTOList.get(1)));
    }

    @Test
    void searchPersonShouldReturnNothing() throws Exception {
        when(personService.searchPerson("Test")).thenReturn(new ArrayList<>());

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("search","Test");

        mvc.perform(MockMvcRequestBuilders
                        .get("/person")
                        .params(params)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void addPerson() {
    }
}