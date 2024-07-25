package com.demobank.atm.domain.model.card;

import org.jmolecules.architecture.hexagonal.Port;
import org.springframework.stereotype.Service;

import com.demobank.atm.domain.model.account.transaction.Transaction;
import com.demobank.atm.domain.model.money.Money;

@Service
@Port
public interface CardService {

	public Transaction debitAmountFromCard(CardNumber cardNumber, Money amount);
    
}
