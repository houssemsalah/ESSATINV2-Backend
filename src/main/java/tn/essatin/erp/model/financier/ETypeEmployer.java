package tn.essatin.erp.model.financier;

public enum ETypeEmployer {
    ADMINISTRATIF("Agent Administratif"),
    ENSEIGNANT("Enseignant"),
    PERSONNEL_ENTRETIEN("Personnel d'entretien");

    private final String value;

    ETypeEmployer(String value) {
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
