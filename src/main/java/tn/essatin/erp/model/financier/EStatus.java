package tn.essatin.erp.model.financier;


public enum EStatus  {
    INCOMPLETE("Incomplete"),
    COMPLETE("Complete"),
    CANCELED("Canceled"),
    REJECTED("Rejected");
    private final String value;
    EStatus(String value) {
        this.value = value;
    }
    public Integer id() {
        return ordinal();
    }
    public String value() {
        return value;
    }
    public String toString() {
        return "" + value + "";
    }
}
