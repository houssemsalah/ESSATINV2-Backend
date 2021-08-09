package tn.essatin.erp.model.financier;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ETypeTransaction {
    DEBIT("Debit"),
    CREDIT("Credit");

    private final String value;

    ETypeTransaction(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value + "";
    }
}
