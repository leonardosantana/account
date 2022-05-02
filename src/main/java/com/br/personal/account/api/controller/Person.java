package com.br.personal.account.api.controller;

import com.br.personal.account.api.dto.AccountDto;
import com.br.personal.account.api.dto.PersonDto;
import com.br.personal.account.model.AccountEntity;
import com.br.personal.account.model.PersonEntity;
import com.br.personal.account.service.AccountService;
import com.br.personal.account.service.PersonService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/personservice")
public class Person {

    @Autowired
    private PersonService service;


    @PostMapping(path = "/person")
    public ResponseEntity<Object> createPerson(@RequestBody final PersonDto person){

        try {
            val personEntity = service.create(person);
            return new ResponseEntity<>("Person created with id: " + personEntity.getIdPerson(), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/person")
    public ResponseEntity<Object> getPersons(){

        List<PersonDto> data = service.findAll();

        return new ResponseEntity<>(data, HttpStatus.OK);

    }


}
