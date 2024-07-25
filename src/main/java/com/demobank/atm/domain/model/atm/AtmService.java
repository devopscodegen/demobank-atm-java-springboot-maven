package com.demobank.atm.domain.model.atm;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demobank.atm.domain.model.account.AccountId;
import com.demobank.atm.domain.model.account.AccountService;
import com.demobank.atm.domain.model.account.AccountType;
import com.demobank.atm.domain.model.account.transaction.Transaction;
import com.demobank.atm.domain.model.card.CardNumber;
import com.demobank.atm.domain.model.card.CardService;
import com.demobank.atm.domain.model.currency.CurrencyCode;
import com.demobank.atm.domain.model.money.Money;

@Service
@org.jmolecules.ddd.annotation.Service
public class AtmService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CardService cardService;

    public Atm newAtm(AtmId atmId, AtmType atmType, CurrencyCode currencyCode) {

        AccountId accountId = new AccountId(
            atmId.getId()
            .add(
                BigInteger.valueOf(3000000)
            )
        );
        this.accountService.openAccount(
            accountId,
            AccountType.valueOf("ATM"),
            currencyCode
        );
        Atm atm = new Atm(
            atmId,
            atmType,
            accountId
        );
        return atm;
    }

    public Transaction withdrawCashFromAtmUsingCard(Atm atm, CardNumber cardNumber, Money amount) {
        this.cardService.debitAmountFromCard(cardNumber, amount);
        return this.accountService.debitAmountFromAccount(atm.getAccountId(), amount);
    }

    public Transaction depositCashIntoAtm(Atm atm, Money amount) {
        return this.accountService.creditAmountToAccount(atm.getAccountId(), amount);
    }
}
