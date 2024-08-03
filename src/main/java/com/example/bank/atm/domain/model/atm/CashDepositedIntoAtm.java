package com.example.bank.atm.domain.model.atm;

import java.util.Date;

import org.jmolecules.event.annotation.DomainEvent;

import com.example.bank.atm.domain.model.account.transaction.Transaction;
import com.example.bank.atm.domain.model.common.BaseDomainEvent;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@DomainEvent
public class CashDepositedIntoAtm extends BaseDomainEvent {
    private Transaction transaction;

    public CashDepositedIntoAtm(Transaction transaction) {
        super();
        this.setTransaction(transaction);
        this.setEventVersion(1);
        this.setOccurredOn(new Date());
        this.setEventType(this.getClass().getName());
    }
}
