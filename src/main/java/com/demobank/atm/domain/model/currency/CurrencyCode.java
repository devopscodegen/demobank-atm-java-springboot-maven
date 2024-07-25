package com.demobank.atm.domain.model.currency;

import com.demobank.atm.domain.model.common.BaseValueObject;

public enum CurrencyCode implements BaseValueObject {

    USD {
        public boolean isUsd() {
            return true;
        }
    },

    AED {
        public boolean isAed() {
            return true;
        }
    };

    public boolean isUsd() {
        return false;
    }

    public boolean isAed() {
        return false;
    }

    public CurrencyCode regress() {
        if (this.isUsd()) {
            return USD;
        } else if (this.isAed()) {
            return AED;
        }

        return USD;
    }
}
