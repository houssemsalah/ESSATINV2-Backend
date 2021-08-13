package tn.essatin.erp.model.financier;

import tn.essatin.erp.util.EnumShape;

public enum EStatus implements EnumShape {
    INCOMPLETE("Incomplete"),
    COMPLETE("Complete"),
    CANCELED("Canceled"),
    REJECTED("Rejected");

    private final String value;

    EStatus(String value) {
        this.value = value;
    }

    @Override
    public Integer id() {
        return ordinal();
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value + "";
    }
}
