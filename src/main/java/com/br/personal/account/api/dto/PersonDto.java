package com.br.personal.account.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    @ApiModelProperty(required = false)
    @JsonProperty(value = "idPerson")
    private String idPerson;

    @ApiModelProperty(required = true)
    @JsonProperty(value = "name")
    private String name;

    @ApiModelProperty(required = true)
    @JsonProperty(value = "document")
    private String document;

    @ApiModelProperty(required = true)
    @JsonProperty(value = "birthDate")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

}