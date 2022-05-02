package com.br.personal.account.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ACCOUNT", schema = "DBACCOUNT")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_account")
    private Long idAccount;

    @OneToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_person", nullable = false)
    private PersonEntity person;

    @Column(name="balance", nullable = false)
    private BigDecimal balance;

    @Column(name="diary_limit", nullable = false)
    private BigDecimal diaryLimit;

    @Column(name="is_active", nullable = false)
    private Boolean isActive;

    @Column(name="account_type", nullable = false)
    private Integer AccountType;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "account")
    List<TransactionEntity> transactions;

}
