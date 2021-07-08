package tn.essatin.erp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TypeIdentificateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeidentificateur;
    private String typeIdentificateur;

    public TypeIdentificateur(String typeIdentificateur) {
        this.typeIdentificateur = typeIdentificateur;
    }

    public TypeIdentificateur() {
    }

    public Integer getIdTypeidentificateur() {
        return idTypeidentificateur;
    }

    public void setIdTypeidentificateur(Integer ID_TypeIdentificateur) {
        this.idTypeidentificateur = ID_TypeIdentificateur;
    }

    public String getTypeIdentificateur() {
        return typeIdentificateur;
    }

    public void setTypeIdentificateur(String typeIdentificateur) {
        this.typeIdentificateur = typeIdentificateur;
    }

    @Override
    public String toString() {
        return "Identificateur{" +
            "ID_TypeIdentificateur=" + idTypeidentificateur +
            ", TypeIdentificateur='" + typeIdentificateur + '\'' +
            '}';
    }


}
