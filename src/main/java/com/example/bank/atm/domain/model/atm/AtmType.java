package com.example.bank.atm.domain.model.atm;

import com.example.bank.atm.domain.model.common.BaseValueObject;

public enum AtmType implements BaseValueObject{

    BASIC {
        public boolean isBasic() {
            return true;
        }
    },

    COMPLEX {
        public boolean isComplex() {
            return true;
        }
    };

    public boolean isBasic() {
        return false;
    }

    public boolean isComplex() {
        return false;
    }

    public AtmType regress() {
        if (this.isBasic()) {
            return BASIC;
        } else if (this.isComplex()) {
            return COMPLEX;
        }

        return BASIC;
    }
}
