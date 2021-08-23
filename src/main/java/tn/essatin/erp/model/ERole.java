package tn.essatin.erp.model;

public enum ERole {
    ROLE_ADMIN("Admin"),
    ROLE_SCOLARITE("Scolarité"),
    ROLE_FINANCIER("Financier"),
    ROLE_EXAMEN("Examen"),
    ROLE_ETUDIANT("Etudiant"),
    ROLE_DIRECTEUR("Directeur");

    private final String value;

    ERole(String value) {
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
