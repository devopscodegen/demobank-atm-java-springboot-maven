package com.demobank.atm.domain.model.atm;

import java.util.Date;

import org.jmolecules.event.annotation.DomainEvent;

import com.demobank.atm.domain.model.common.BaseDomainEvent;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@DomainEvent
public class AtmCreated extends BaseDomainEvent {
    private Atm atm;

    public AtmCreated(Atm atm) {
        super();
        this.setAtm(atm);
        this.setEventVersion(1);
        this.setOccurredOn(new Date());
        this.setEventType(this.getClass().getName());
    }
}
