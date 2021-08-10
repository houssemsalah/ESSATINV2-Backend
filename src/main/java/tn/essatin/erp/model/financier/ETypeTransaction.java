package tn.essatin.erp.model.financier;

public enum ETypeTransaction {
    DEBIT("debit"),
    CREDIT("credit");

    private final String value;

    ETypeTransaction(String value) {
        this.value = value;
    }
    public String value() {return value;}

    @Override
    public String toString() {
        return "ETypeTransaction{" +
                "value='" + value + '\'' +
                '}';
    }
}
