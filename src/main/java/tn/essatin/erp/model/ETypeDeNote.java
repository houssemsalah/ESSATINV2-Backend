package tn.essatin.erp.model;

public enum ETypeDeNote {
    EXAMEN("Examen"),
    DS("DS"),
    TP("TP"),
    CONTROLE("Controle"),
    ORALE("Orale");
    private final String value;

    ETypeDeNote(String value) {
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
