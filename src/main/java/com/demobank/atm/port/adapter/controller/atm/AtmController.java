package com.demobank.atm.port.adapter.controller.atm;

import java.math.BigInteger;

import org.jmolecules.architecture.hexagonal.Adapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demobank.atm.application.atm.AtmApplicationService;
import com.demobank.atm.application.atm.DepositCashIntoAtmCommand;
import com.demobank.atm.application.atm.NewAtmCommand;
import com.demobank.atm.application.atm.WithdrawCashFromAtmUsingCardCommand;
import com.demobank.atm.domain.model.account.transaction.Transaction;

@RestController
@RequestMapping("/api/v1/atm")
@Adapter
public class AtmController {
    @Autowired
    private AtmApplicationService atmApplicationService;

    @PostMapping("/{atmId}")
    public NewAtmResponse newAtm(@PathVariable BigInteger atmId, @RequestBody NewAtmRequest newAtmRequest) {
        this.atmApplicationService.newAtm(
            new NewAtmCommand(
                atmId,
                newAtmRequest.getAtmType(),
                newAtmRequest.getCurrencyCode()
            )
        );
        
        return new NewAtmResponse(
            "SUCCESS");
    }

    @PostMapping("/{atmId}/withdraw")
    public WithdrawCashFromAtmUsingCardResponse withdrawCashFromAtmUsingCard(@PathVariable BigInteger atmId, @RequestBody WithdrawCashFromAtmUsingCardRequest withdrawCashFromAtmRequest) {
        Transaction transaction = this.atmApplicationService.withdrawCashFromAtmUsingCard(
            new WithdrawCashFromAtmUsingCardCommand(
                atmId,
                withdrawCashFromAtmRequest.getCardNumber(),
                withdrawCashFromAtmRequest.getAmount(), 
                withdrawCashFromAtmRequest.getCurrencyCode()));
                
        return new WithdrawCashFromAtmUsingCardResponse(
            transaction.getTransactionStatus().toString(), 
            transaction.getTransactionId().getId(),
            transaction.getNewBalance().getAmount(), 
            transaction.getNewBalance().getCurrencyCode().toString()
        );
    }

    @PostMapping("/{atmId}/deposit")
    public DepositCashIntoAtmResponse depositCashIntoAtm(@PathVariable BigInteger atmId, @RequestBody DepositCashIntoAtmRequest depositCashIntoAtmRequest) {
        Transaction transaction = this.atmApplicationService.depositCashIntoAtm(
            new DepositCashIntoAtmCommand(
                atmId,
                depositCashIntoAtmRequest.getAmount(), 
                depositCashIntoAtmRequest.getCurrencyCode()));
                
        return new DepositCashIntoAtmResponse(
            transaction.getTransactionStatus().toString(), 
            transaction.getTransactionId().getId(),
            transaction.getNewBalance().getAmount(), 
            transaction.getNewBalance().getCurrencyCode().toString()
        );
    }
}