package com.br.personal.account.api.validator;

import com.br.personal.account.api.dto.TransactionDto;
import com.br.personal.account.model.AccountEntity;
import com.br.personal.account.repository.TransactionRepository;
import com.br.personal.account.service.AccountService;
import com.br.personal.account.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DebitTransaction implements Transaction{


    @Override
    public void checkTransaction(AccountEntity account, TransactionDto transactionDto, TransactionService service) {
        checkTransactionAmount(transactionDto);
        checkIfAccountHasFund(account, transactionDto);
        checkDailyMovimentOperation(account, transactionDto, service);
    }

    private void checkDailyMovimentOperation(AccountEntity account, TransactionDto transactionDto, TransactionService service) {

        BigDecimal dailyMoviments = service.getDailyDebitMovements(account, transactionDto.getOperation());


        Assert.isTrue(dailyMoviments.add(transactionDto.getAmmount()).compareTo(account.getDiaryLimit())<=0, "Daily limit was exceeded");

    }

    private void checkIfAccountHasFund(AccountEntity account, TransactionDto transactionDto) {
        Assert.isTrue(account.getBalance().subtract(transactionDto.getAmmount()).intValue() >= 0, "Account has no fund");
    }
}
