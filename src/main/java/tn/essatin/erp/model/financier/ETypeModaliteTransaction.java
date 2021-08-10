package tn.essatin.erp.model.financier;

public enum ETypeModaliteTransaction {
    ESPECES("espèces"),
    CARTE_BANCAIRE("carte bancaire"),
    VIREMENT_BANCAIRE("virement bancaire"),
    CHEQUE("chèque");

    private final String value;

    ETypeModaliteTransaction(String value) {
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
