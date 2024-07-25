package com.demobank.atm.domain.model.account.transaction;

import org.jmolecules.ddd.annotation.ValueObject;

import com.demobank.atm.domain.model.account.AccountId;
import com.demobank.atm.domain.model.common.BaseValueObject;
import com.demobank.atm.domain.model.money.Money;

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
@ValueObject
public class Transaction implements BaseValueObject {

    private TransactionId transactionId;
    private AccountId accountId;
    private Money amount;
    private TransactionStatus transactionStatus;
    private Money newBalance;
}
