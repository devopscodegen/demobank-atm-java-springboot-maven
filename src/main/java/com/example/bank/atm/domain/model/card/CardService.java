package com.example.bank.atm.domain.model.card;

import org.jmolecules.architecture.hexagonal.Port;
import org.springframework.stereotype.Service;

import com.example.bank.atm.domain.model.account.transaction.Transaction;
import com.example.bank.atm.domain.model.money.Money;

@Service
@Port
public interface CardService {

	public Transaction debitAmountFromCard(CardNumber cardNumber, Money amount);
    
}
