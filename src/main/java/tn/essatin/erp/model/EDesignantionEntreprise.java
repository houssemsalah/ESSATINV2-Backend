package tn.essatin.erp.model;

public enum EDesignantionEntreprise {
    SOCIETE("la societé"),
    ENTREPRISE("l'entreprise");

    private final String value;

    EDesignantionEntreprise(String value) {
        this.value = value;
    }
    public String value() {return value;}

    @Override
    public String toString() { return ""+ value ;}
}
