package tn.essatin.erp.model.financier;

public enum ESituationMaritale {

    CELIBATAIRE("Célibataire"), MARIEE("mariée"), VEUVE("veuve"), DIVORCEE("divorcée");


    private final String value;

    ESituationMaritale(String value) {
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
