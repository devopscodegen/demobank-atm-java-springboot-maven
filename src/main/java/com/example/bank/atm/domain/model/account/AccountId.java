package com.example.bank.atm.domain.model.account;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import org.jmolecules.ddd.annotation.ValueObject;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.util.Assert;

import com.example.bank.atm.domain.model.common.BaseValueObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@ValueObject
public class AccountId implements Serializable, Comparable<AccountId>, BaseValueObject{

    private static final long serialVersionUID = 1L;
    private BigInteger id;
    private void setId(BigInteger id) {
        Objects.requireNonNull(id, "Account Id must not be empty");
        Assert.isTrue(id.compareTo(BigInteger.ZERO) > 0, "Account Id must be greater than zero");
        this.id = id;
    }
    @PersistenceCreator
    public AccountId(BigInteger id) {
        super();
        this.setId(id);
    }
    @Override
    public int compareTo(AccountId AccountId) {
        return this.getId().compareTo(AccountId.getId());
    }
}

