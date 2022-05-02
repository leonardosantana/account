package com.br.personal.account.api.controller;

import com.br.personal.account.model.PersonEntity;
import com.br.personal.account.service.PersonService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Account.class)
@AutoConfigureMockMvc
public class AccountTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonService service;

    @Test
    public void makeAccountRequestWithOKStatus() throws Exception {

        mvc.perform(get("/accountservice/person"))
                .andExpect(status().isOk());

    }

    @Test
    public void makeAccountRequestWithAPersonInResult() throws Exception {

        //when(service.findAll()).thenReturn(getPersons());

        val response = mvc.perform(get("/accountservice/person"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();



        assertEquals(false, response.getContentAsString().isEmpty());
    }

    public Page<PersonEntity> getPersons(){

        ArrayList<PersonEntity> persons = new ArrayList<>();
        persons.add(new PersonEntity());

        return new PageImpl<>(persons, PageRequest.of(0,10),persons.size());
    }

}
