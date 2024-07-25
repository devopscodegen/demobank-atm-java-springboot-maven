package com.demobank.atm.port.adapter.service.account;

import org.jmolecules.architecture.hexagonal.Adapter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.demobank.atm.domain.model.account.AccountId;
import com.demobank.atm.domain.model.account.AccountService;
import com.demobank.atm.domain.model.account.AccountType;
import com.demobank.atm.domain.model.account.transaction.Transaction;
import com.demobank.atm.domain.model.account.transaction.TransactionId;
import com.demobank.atm.domain.model.account.transaction.TransactionStatus;
import com.demobank.atm.domain.model.currency.CurrencyCode;
import com.demobank.atm.domain.model.money.Money;
import com.demobank.atm.port.adapter.service.account.transaction.TransactionRequest;
import com.demobank.atm.port.adapter.service.account.transaction.TransactionResponse;

@Service
@Adapter
public class RESTAccountService implements AccountService {

    private String baseUrl;

    private RestClient restClient;

    private RestClient.Builder restClientBuilder;

    public RESTAccountService() {
        super();
        this.setBaseUrl("http://localhost:8080/api/v1/account");
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

    public void openAccount(AccountId accountId, AccountType accountType, CurrencyCode balanceCurrencyCode) {
        this.getRestClient().post()
        .uri("/{accountId}", accountId.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .body(new OpenAccountRequest(
            accountType.toString(), 
            balanceCurrencyCode.toString())
        )
        .retrieve()
        .body(OpenAccountResponse.class);
    }

    public Transaction debitAmountFromAccount(AccountId accountId, Money amount) {
        TransactionResponse transactionResponse = this.getRestClient().post()
            .uri("/{accountId}/debit", accountId.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .body(new TransactionRequest(
                amount.getAmount(), 
                amount.getCurrencyCode().toString())
            )
            .retrieve()
            .body(TransactionResponse.class);
        
        return new Transaction(
            new TransactionId(transactionResponse.getTransactionId()), 
            accountId, 
            amount,
            TransactionStatus.valueOf(transactionResponse.getStatus()), 
            new Money(
                transactionResponse.getNewBalance(), 
                CurrencyCode.valueOf(transactionResponse.getNewBalanceCurrencyCode())
            )
        );
    }

    public Transaction creditAmountToAccount(AccountId accountId, Money amount) {
        TransactionResponse transactionResponse = this.getRestClient().post()
            .uri("/{accountId}/credit", accountId.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .body(new TransactionRequest(
                amount.getAmount(), 
                amount.getCurrencyCode().toString())
            )
            .retrieve()
            .body(TransactionResponse.class);
        
        return new Transaction(
            new TransactionId(transactionResponse.getTransactionId()), 
            accountId, 
            amount,
            TransactionStatus.valueOf(transactionResponse.getStatus()), 
            new Money(
                transactionResponse.getNewBalance(), 
                CurrencyCode.valueOf(transactionResponse.getNewBalanceCurrencyCode())
            )
        );
    }
}
