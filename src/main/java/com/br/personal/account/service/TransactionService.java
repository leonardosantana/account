package com.br.personal.account.service;

import com.br.personal.account.api.dto.TransactionDto;
import com.br.personal.account.api.validator.CreditTransaction;
import com.br.personal.account.api.validator.DebitTransaction;
import com.br.personal.account.api.validator.Transaction;
import com.br.personal.account.model.AccountEntity;
import com.br.personal.account.model.TransactionEntity;
import com.br.personal.account.repository.AccountRepository;
import com.br.personal.account.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;
    public List<TransactionDto> getTransactions(Long id) {

        Optional<AccountEntity> entity  = accountRepository.findById(id);

        List<TransactionEntity> transactions =  transactionRepository.findByAccount(entity.get());

        return transactions.stream().map(transactionEntityToDto()).collect(Collectors.toList());
    }

    private Function<TransactionEntity, TransactionDto> transactionEntityToDto() {
        return transaction -> new ModelMapper().map(transaction, TransactionDto.class);
    }

    public TransactionEntity putTransactionInAcount(Long id, TransactionDto transactionDto) {

        TransactionEntity entity = new ModelMapper().map(transactionDto, TransactionEntity.class);

        entity.setAccount(accountRepository.findById(id).get());
        entity.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(entity);

        return entity;

    }

    public BigDecimal getDailyDebitMovements(AccountEntity account, String operation){
        return transactionRepository.sumTransactionByday(account,
                LocalDate.now().atStartOfDay(),
                LocalDateTime.now(),
                operation).orElse(new BigDecimal(0));
    }

    public void checkTransaction(AccountEntity account, TransactionDto transactionDto) throws Exception {

        Transaction checkTransaction = null;

        if(transactionDto.getOperation().equalsIgnoreCase("DEPOSITO")) {
            checkTransaction = new CreditTransaction();
        }

        if(transactionDto.getOperation().equalsIgnoreCase("SAQUE")) {
            checkTransaction = new DebitTransaction();
        }

        checkTransaction.checkTransaction(account,transactionDto, this);

    }
}
