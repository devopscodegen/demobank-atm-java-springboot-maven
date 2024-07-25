package com.demobank.atm.port.adapter.controller.atm;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class DepositCashIntoAtmResponse {
    private String status;
    private UUID transactionId;
    private BigDecimal newBalance;
    private String newBalanceCurrencyCode;
}