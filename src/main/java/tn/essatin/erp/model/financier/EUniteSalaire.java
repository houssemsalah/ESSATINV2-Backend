package tn.essatin.erp.model.financier;

public enum EUniteSalaire {
    HEURES("Heurs"),
    JOURS("Jours"),
    SEMAINES("Semaines"),
    QUINZAINE("Quinzaine"),
    MOIS("Mois");

    private final String value;

    EUniteSalaire(String value) {
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
