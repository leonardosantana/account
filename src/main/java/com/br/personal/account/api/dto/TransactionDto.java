package com.br.personal.account.api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    @ApiModelProperty(required = true)
    @JsonProperty(value = "transactionDate")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime transactionDate;

    @ApiModelProperty(required = true)
    @JsonProperty(value = "ammount")
    private BigDecimal ammount;

    @ApiModelProperty(required = true)
    @JsonProperty(value = "operation")
    private String operation;
}
