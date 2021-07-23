package tn.essatin.erp.model.financier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TypeTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeTransaction;
    private String libelle;

    public TypeTransaction() {
    }

    public TypeTransaction(Integer idTypeTransaction, String libelle) {
        this.idTypeTransaction = idTypeTransaction;
        this.libelle = libelle;
    }

    public Integer getIdTypeTransaction() {
        return idTypeTransaction;
    }

    public void setIdTypeTransaction(Integer idTypeTransaction) {
        this.idTypeTransaction = idTypeTransaction;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "TypeTransaction{" +
            "idTypeTransaction=" + idTypeTransaction +
            ", libelle='" + libelle + '\'' +
            '}';
    }
}
