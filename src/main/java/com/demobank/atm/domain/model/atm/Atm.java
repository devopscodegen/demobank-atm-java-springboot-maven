package com.demobank.atm.domain.model.atm;

import org.jmolecules.ddd.annotation.AggregateRoot;

import com.demobank.atm.domain.model.account.AccountId;
import com.demobank.atm.domain.model.common.BaseAggregateRoot;

import jakarta.annotation.Nullable;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="atms")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString
@AggregateRoot
public class Atm extends BaseAggregateRoot<Atm, AtmId> {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private AtmId atmId;
    private AtmType atmType;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(
            name="id", 
            column=@jakarta.persistence.Column(name="account_id"))
    })
    private AccountId accountId;
    public Atm(AtmId atmId, AtmType atmType, AccountId accountId) {
        super();
        this.setAtmId(atmId);
        this.setAtmType(atmType);
        this.setAccountId(accountId);
        registerEvent(new AtmCreated(this));
    }

    @Nullable
	@Override
	public AtmId getId() {
		return this.getAtmId();
	}
	@Transient
	@Override
	public boolean isNew() {
		return null == this.getId();
	}
}
