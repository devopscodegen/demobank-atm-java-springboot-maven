package com.demobank.atm.port.adapter.service.card;

import org.jmolecules.architecture.hexagonal.Adapter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.demobank.atm.domain.model.account.AccountId;
import com.demobank.atm.domain.model.account.transaction.Transaction;
import com.demobank.atm.domain.model.account.transaction.TransactionId;
import com.demobank.atm.domain.model.account.transaction.TransactionStatus;
import com.demobank.atm.domain.model.card.CardNumber;
import com.demobank.atm.domain.model.card.CardService;
import com.demobank.atm.domain.model.currency.CurrencyCode;
import com.demobank.atm.domain.model.money.Money;
import com.demobank.atm.port.adapter.service.card.transaction.TransactionRequest;
import com.demobank.atm.port.adapter.service.card.transaction.TransactionResponse;

@Service
@Adapter
public class RESTCardService implements CardService {

    private String baseUrl;

    private RestClient restClient;

    private RestClient.Builder restClientBuilder;

    public RESTCardService() {
        super();
        this.setBaseUrl("http://localhost:8079/api/v1/card");
        this.setRestClientBuilder(RestClient.builder());
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(this.getBaseUrl());
        this.setRestClient(this.getRestClientBuilder().uriBuilderFactory(factory).build());
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    private void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public RestClient getRestClient() {
        return restClient;
    }

    private void setRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public RestClient.Builder getRestClientBuilder() {
        return restClientBuilder;
    }

    private void setRestClientBuilder(RestClient.Builder restClientBuilder) {
        this.restClientBuilder = restClientBuilder;
    }

    public Transaction debitAmountFromCard(CardNumber cardNumber, Money amount) {
        TransactionResponse transactionResponse = this.getRestClient().post()
            .uri("/{cardNumber}/debit", cardNumber.getNumber())
            .contentType(MediaType.APPLICATION_JSON)
            .body(new TransactionRequest(
                amount.getAmount(), 
                amount.getCurrencyCode().toString())
            )
            .retrieve()
            .body(TransactionResponse.class);
        
        return new Transaction(
            new TransactionId(transactionResponse.getTransactionId()), 
            new AccountId(transactionResponse.getAccountId()), 
            amount,
            TransactionStatus.valueOf(transactionResponse.getStatus()), 
            new Money(
                transactionResponse.getNewBalance(), 
                CurrencyCode.valueOf(transactionResponse.getNewBalanceCurrencyCode())
            )
        );
    }
}
