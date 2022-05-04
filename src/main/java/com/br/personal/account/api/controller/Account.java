package com.br.personal.account.api.controller;

import com.br.personal.account.api.dto.AccountDto;
import com.br.personal.account.model.AccountEntity;
import com.br.personal.account.service.AccountService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/accountservice")
public class Account {

    @Autowired
    private AccountService service;


    @PostMapping(path = "/account")
public ResponseEntity<Object> createAccount(@RequestBody final AccountDto account){

        try {
            val accountEntity = service.create(account);
            return new ResponseEntity<>("Account created with number: " + accountEntity.getIdAccount(), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/account")
    public ResponseEntity<Object> getAccounts(){

        List<AccountDto> data = service.findAll();

        return new ResponseEntity<>(data, HttpStatus.OK);

    }

    @GetMapping(path = "/account/{idAccount}")
    public ResponseEntity<Object> getAccount(@PathVariable("idAccount") Long id){

        AccountDto data = service.findOne(id);

        return new ResponseEntity<>(data, HttpStatus.OK);

    }

    @PatchMapping(path = "/account/{idAccount}/lock")
    public ResponseEntity<Object> lockAccount(@PathVariable("idAccount") Long id){

        AccountDto data = service.changeStatus(id, false);

        return new ResponseEntity<>(data, HttpStatus.OK);

    }

    @PatchMapping(path = "/account/{idAccount}/unlock")
    public ResponseEntity<Object> unlockAccount(@PathVariable("idAccount") Long id){

        AccountDto data = service.changeStatus(id, true);

        return new ResponseEntity<>(data, HttpStatus.OK);

    }
}
