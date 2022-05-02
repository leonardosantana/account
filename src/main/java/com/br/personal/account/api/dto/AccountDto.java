package com.br.personal.account.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto implements Serializable {

    @ApiModelProperty(required = true)
    @JsonProperty(value = "type")
    private Integer type;

    @ApiModelProperty(required = true)
    @JsonProperty(value = "idPerson")
    private Long idPerson;

    @ApiModelProperty(required = true)
    @JsonProperty(value = "isActive")
    private Boolean isActive;

    @ApiModelProperty(required = true)
    @JsonProperty(value = "diaryLimit")
    private BigDecimal diaryLimit;

    @ApiModelProperty(required = true)
    @JsonProperty(value = "balance")
    private BigDecimal balance;

}
