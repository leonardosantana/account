package com.br.personal.account.service;

import com.br.personal.account.api.dto.AccountDto;
import com.br.personal.account.model.AccountEntity;
import com.br.personal.account.model.PersonEntity;
import com.br.personal.account.model.TransactionEntity;
import com.br.personal.account.repository.AccountRepository;
import com.br.personal.account.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PersonRepository personRepository;

    public List<AccountDto> findAll() {

        return accountRepository.findAll().stream().map(accountEntityToDto()).collect(Collectors.toList());
    }

    private Function<AccountEntity, AccountDto> accountEntityToDto() {
        return e -> new ModelMapper().map(e, AccountDto.class);
    }

    public AccountDto findOne(Long id) {
        Optional<AccountEntity> account = accountRepository.findById(id);
        return new ModelMapper().map(account.get(), AccountDto.class);
    }

    public AccountDto changeStatus(Long id, Boolean isActive) {
        AccountEntity account = accountRepository.findById(id).get();

        account.setIsActive(isActive);

        accountRepository.save(account);
        return new ModelMapper().map(account, AccountDto.class);
    }

    public AccountEntity create(AccountDto accountDto){

        PersonEntity person = personRepository.getById(accountDto.getIdPerson());
        Assert.notNull(person,"Account owner not exists");

        AccountEntity account = new ModelMapper().map(accountDto, AccountEntity.class);

        account.setBalance(new BigDecimal("0"));
        account.setPerson(person);
        account.setCreatedAt(LocalDateTime.now());

        accountRepository.save(account);

        return account;
    }

    public AccountEntity checkAccount(Long id, BigDecimal ammount) {

        AccountEntity entity = accountRepository.findById(id).get();

        Assert.notNull(entity, "Account not found");
        Assert.isTrue(entity.getIsActive(), "Account is blocked");

        return entity;
    }

    public void updateAccount(Long id, TransactionEntity transaction) {

        AccountEntity entity = accountRepository.getById(id);

        entity.getTransactions().add(transaction);

        entity.setBalance(updateBalance(transaction, entity.getBalance()));

        accountRepository.save(entity);

    }

    private BigDecimal updateBalance(TransactionEntity transaction, BigDecimal balance) {
        return transaction.getOperation() == "DEPOSITO" ?
                balance.add(transaction.getAmmount()) :
                balance.subtract(transaction.getAmmount());
    }
}
