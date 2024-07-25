package com.demobank.atm.domain.model.atm;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import org.jmolecules.ddd.annotation.ValueObject;
import org.jmolecules.ddd.types.Identifier;
import org.springframework.util.Assert;

import com.demobank.atm.domain.model.common.BaseValueObject;

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
public class AtmId implements Serializable, Comparable<AtmId>, Identifier, BaseValueObject{

    private static final long serialVersionUID = 1L;
    private BigInteger id;
    private void setId(BigInteger id) {
        Objects.requireNonNull(id, "Account Id must not be empty");
        Assert.isTrue(id.compareTo(BigInteger.ZERO) > 0, "Account Id must be greater than zero");
        this.id = id;
    }
    public AtmId(BigInteger id) {
        super();
        this.setId(id);
    }
    @Override
    public int compareTo(AtmId atmId) {
        return this.getId().compareTo(atmId.getId());
    }
}
