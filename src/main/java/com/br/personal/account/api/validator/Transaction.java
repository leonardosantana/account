package com.br.personal.account.api.validator;

import com.br.personal.account.api.dto.TransactionDto;
import com.br.personal.account.model.AccountEntity;
import com.br.personal.account.service.TransactionService;
import org.springframework.util.Assert;

public interface Transaction {
    void checkTransaction(AccountEntity account, TransactionDto transactionDto, TransactionService service);

    default void checkTransactionAmount(TransactionDto transactionDto){
        Assert.isTrue(transactionDto.getAmmount().intValue()>0, "Invalid operation amount");
    }
}
