package com.example.bank.atm.port.adapter.controller.atm;

import java.math.BigDecimal;
import java.math.BigInteger;

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
public class WithdrawCashFromAtmUsingCardRequest {
    private BigInteger cardNumber;
    private BigDecimal amount;
    private String currencyCode;
}