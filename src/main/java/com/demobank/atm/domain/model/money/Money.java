package com.demobank.atm.domain.model.money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import org.jmolecules.ddd.annotation.ValueObject;
import org.springframework.util.Assert;

import com.demobank.atm.domain.model.common.BaseValueObject;
import com.demobank.atm.domain.model.currency.CurrencyCode;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@ValueObject
public class Money implements Serializable, Comparable<Money>, BaseValueObject{

    private static final long serialVersionUID = 1L;
    private BigDecimal amount;
    private CurrencyCode currencyCode;
    public Money(BigDecimal amount, CurrencyCode currencyCode) {
        this.setAmount(amount);
        this.setCurrencyCode(currencyCode);
    }
    public BigDecimal getAmount() {
        return amount;
    }
    private void setAmount(BigDecimal amount) {
        Objects.requireNonNull(amount, "amount must not be empty");
        this.amount = amount;
    }
    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }
    private void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }
    public Money add(Money money) {
        Assert.isTrue(this.getCurrencyCode().equals(money.getCurrencyCode()), "money objects must have same currency code");
        return new Money(
            this.getAmount().add(money.getAmount()),
            this.getCurrencyCode());
    }
    public Money subtract(Money money) {
        Assert.isTrue(this.getCurrencyCode().equals(money.getCurrencyCode()), "money objects must have same currency code");
        return new Money(
            this.getAmount().subtract(money.getAmount()),
            this.getCurrencyCode());
    }
    @Override
    public int compareTo(Money Money) {
        return this.getAmount().compareTo(Money.getAmount());
    }
}
