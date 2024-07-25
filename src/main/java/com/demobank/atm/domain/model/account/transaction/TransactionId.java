package com.demobank.atm.domain.model.account.transaction;

import java.io.Serializable;
import java.util.UUID;

import org.jmolecules.ddd.annotation.ValueObject;
import org.jmolecules.ddd.types.Identifier;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.util.Assert;

import com.demobank.atm.domain.model.common.BaseValueObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@ValueObject
public class TransactionId implements Serializable, Comparable<TransactionId>, Identifier, BaseValueObject{

    private static final long serialVersionUID = 1L;
    private UUID id;
    private void setId(UUID id) {
        Assert.hasLength(id.toString(), "Transaction Id must not be empty");
        this.id = id;
    }
    @PersistenceCreator
    public TransactionId(UUID id) {
        super();
        this.setId(id);
    }
    public TransactionId() {
        super();
        this.setId(UUID.randomUUID());
    }
    @Override
    public int compareTo(TransactionId TransactionId) {
        return this.getId().compareTo(TransactionId.getId());
    }
}
