package tn.essatin.erp.model.financier;

public enum ETypeContrat {
    CDI("Contrat a Durée Indéterminé"),
    CDD("Contrat a Durée Déterminé"),
    VACATION("Contrat de Vacation");

    private final String value;

    ETypeContrat(String value) {
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
