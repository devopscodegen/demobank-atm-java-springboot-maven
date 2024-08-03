package com.example.bank.atm.domain.model.account;

import com.example.bank.atm.domain.model.common.BaseValueObject;

public enum AccountType implements BaseValueObject{

    SAVINGS {
        public boolean isSavings() {
            return true;
        }
    },

    CURRENT {
        public boolean isCurrent() {
            return true;
        }
    },

    LOAN {
        public boolean isLoan() {
            return true;
        }
    },

    ATM {
        public boolean isAtm() {
            return true;
        }
    },

    CARD {
        public boolean isCard() {
            return true;
        }
    };
    public boolean isSavings() {
        return false;
    }

    public boolean isCurrent() {
        return false;
    }

    public boolean isLoan() {
        return false;
    }

    public boolean isAtm() {
        return false;
    }

    public boolean isCard() {
        return false;
    }
    public AccountType regress() {
        if (this.isSavings()) {
            return SAVINGS;
        } else if (this.isCurrent()) {
            return CURRENT;
        }else if (this.isLoan()) {
            return LOAN;
        }else if (this.isAtm()) {
            return ATM;
        }else if (this.isCard()) {
            return CARD;
        }

        return SAVINGS;
    }
}
