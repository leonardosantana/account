package com.br.personal.account.model;

import com.br.personal.account.api.controller.Account;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TRANSACTION", schema = "DBACCOUNT")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Long idTransaction;

    @ManyToOne
    private AccountEntity account;

    @Column(name="ammount", nullable = false)
    private BigDecimal ammount;

    @Column(name="transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Column(name="operation", nullable = false)
    private String operation;
}
