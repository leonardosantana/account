package com.br.personal.account.api.validator;

import com.br.personal.account.api.dto.TransactionDto;
import com.br.personal.account.model.AccountEntity;
import com.br.personal.account.service.TransactionService;

public class CreditTransaction implements Transaction {


    @Override
    public void checkTransaction(AccountEntity account, TransactionDto transactionDto, TransactionService service) {

        checkTransactionAmount(transactionDto);
    }
}
