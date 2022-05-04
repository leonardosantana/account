package com.br.personal.account.repository;

import com.br.personal.account.api.controller.Account;
import com.br.personal.account.model.AccountEntity;
import com.br.personal.account.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository <TransactionEntity, Long> {

    @Query(
            "SELECT t FROM TransactionEntity t " +
            "WHERE t.account = ?1"
    )
    List<TransactionEntity> findByAccount(AccountEntity account);

    @Query(
            "SELECT sum(t.ammount) FROM TransactionEntity t " +
                    "WHERE t.account = :account " +
                    "and t.operation = :operation " +
                    "and t.transactionDate >= :start " +
                    "and t.transactionDate <= :end ")
    Optional<BigDecimal> sumTransactionByday(AccountEntity account, LocalDateTime start, LocalDateTime end, String operation);
}
