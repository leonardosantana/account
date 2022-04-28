package com.br.personal.account.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accountservice")
public class Account {

    @GetMapping(path = "/person")
    public ResponseEntity<Object> getPersons(){

        return new ResponseEntity<>(null, HttpStatus.OK);

    }
}
