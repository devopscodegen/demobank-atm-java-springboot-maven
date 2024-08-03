package com.example.bank.atm.port.adapter.service.card.transaction;

import java.math.BigDecimal;
import java.math.BigInteger;
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
public class TransactionResponse {
    private BigInteger accountId;
    private String status;
    private UUID transactionId;
    private BigDecimal newBalance;
    private String newBalanceCurrencyCode;
}