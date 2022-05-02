package com.br.personal.account.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name="PERSON", schema = "DBACCOUNT")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_person")
    private Long idPerson;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="document", nullable = false)
    private String document;

    @Column(name="birth_date", nullable = false)
    private LocalDate birthDate;

}
