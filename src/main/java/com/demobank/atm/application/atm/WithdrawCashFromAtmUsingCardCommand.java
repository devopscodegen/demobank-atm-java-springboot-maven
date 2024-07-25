package com.demobank.atm.application.atm;

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
public class WithdrawCashFromAtmUsingCardCommand {
    private BigInteger atmId;
    private BigInteger cardNumber;
    private BigDecimal amount;
    private String currencyCode;
}
