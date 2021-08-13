package tn.essatin.erp.model.financier;

public enum ETypePrestataire {
    FORMATEUR("Formateur"),
    FOURNISSEUR("Fournisseur"),
    INDEPENDANT("Travailleur Indépendant");

    private final String value;

    ETypePrestataire(String value) {
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
