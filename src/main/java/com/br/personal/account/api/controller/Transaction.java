package com.br.personal.account.api.controller;


import com.br.personal.account.api.dto.TransactionDto;
import com.br.personal.account.model.AccountEntity;
import com.br.personal.account.model.TransactionEntity;
import com.br.personal.account.service.AccountService;
import com.br.personal.account.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountservice")
public class Transaction {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @GetMapping(path = "account/{idAccount}/transaction")
    public ResponseEntity<Object> getTransactions(@PathVariable("idAccount") Long id){

        List<TransactionDto> transactions = transactionService.getTransactions(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);

    }


    @PostMapping(path = "account/{idAccount}/transaction")
    public ResponseEntity<Object> putTransactions(@PathVariable("idAccount") Long id, @RequestBody final TransactionDto transactionDto){

        try{

            AccountEntity entity = accountService.checkAccount(id, transactionDto.getAmmount());
            transactionService.checkTransaction(entity, transactionDto);

            TransactionEntity transaction = transactionService.putTransactionInAcount(id, transactionDto);

            accountService.updateAccount(id, transaction);

            return new ResponseEntity<>("Operação de: " +transaction.getAmmount() + " efetivada na conta: " + id     , HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
