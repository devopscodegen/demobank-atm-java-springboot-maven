package com.example.bank.atm.application.atm;

import org.jmolecules.architecture.hexagonal.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bank.atm.domain.model.account.transaction.Transaction;
import com.example.bank.atm.domain.model.atm.Atm;
import com.example.bank.atm.domain.model.atm.AtmId;
import com.example.bank.atm.domain.model.atm.AtmRepository;
import com.example.bank.atm.domain.model.atm.AtmService;
import com.example.bank.atm.domain.model.atm.AtmType;
import com.example.bank.atm.domain.model.card.CardNumber;
import com.example.bank.atm.domain.model.currency.CurrencyCode;
import com.example.bank.atm.domain.model.money.Money;

@Service
@Application
public class AtmApplicationService {

    @Autowired
    private AtmRepository atmRepository;

    @Autowired
    private AtmService atmService;

    @Transactional
    public Atm newAtm(NewAtmCommand newAtmCommand) {
        Atm atm = this.atmService.newAtm(
            new AtmId(newAtmCommand.getAtmId()),
            AtmType.valueOf(newAtmCommand.getAtmType()),
            CurrencyCode.valueOf(newAtmCommand.getCurrencyCode())
        );
        
        atm = this.atmRepository.save(atm);

        return atm;
    }
    @Transactional
    public Transaction withdrawCashFromAtmUsingCard(WithdrawCashFromAtmUsingCardCommand withdrawCashFromAtmUsingCardCommand) {

        Atm atm = this.atmRepository.findById(new AtmId(withdrawCashFromAtmUsingCardCommand.getAtmId())).get();

        CardNumber cardNumber = new CardNumber(withdrawCashFromAtmUsingCardCommand.getCardNumber());

        Money amount = new Money(
            withdrawCashFromAtmUsingCardCommand.getAmount(), 
            CurrencyCode.valueOf(withdrawCashFromAtmUsingCardCommand.getCurrencyCode())
        );
        return this.atmService.withdrawCashFromAtmUsingCard(
            atm,
            cardNumber,
            amount
        );
    }

    @Transactional
    public Transaction depositCashIntoAtm(DepositCashIntoAtmCommand depositCashIntoAtmCommand) {

        Atm atm = this.atmRepository.findById(new AtmId(depositCashIntoAtmCommand.getAtmId())).get();

        Money amount = new Money(
            depositCashIntoAtmCommand.getAmount(), 
            CurrencyCode.valueOf(depositCashIntoAtmCommand.getCurrencyCode())
        );
        return this.atmService.depositCashIntoAtm(
            atm,
            amount
        );
    }
}
