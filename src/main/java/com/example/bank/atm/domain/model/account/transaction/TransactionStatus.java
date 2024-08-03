package com.example.bank.atm.domain.model.account.transaction;

import org.jmolecules.ddd.annotation.ValueObject;

import com.example.bank.atm.domain.model.common.BaseValueObject;

@ValueObject
public enum TransactionStatus implements BaseValueObject{

    SUCCESS {
        public boolean isSuccess() {
            return true;
        }
    },

    FAILED {
        public boolean isFailed() {
            return true;
        }
    };

    public boolean isSuccess() {
        return false;
    }

    public boolean isFailed() {
        return false;
    }

    public TransactionStatus regress() {
        if (this.isSuccess()) {
            return SUCCESS;
        } else if (this.isFailed()) {
            return FAILED;
        }

        return SUCCESS;
    }
}
