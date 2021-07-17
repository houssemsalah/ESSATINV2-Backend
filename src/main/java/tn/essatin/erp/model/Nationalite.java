package tn.essatin.erp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nationalite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_Nationalite;
    private String libelle;

    public Nationalite(String libelle) {

        this.libelle = libelle;
    }

    public Nationalite() {
    }

    public Integer getID_Nationalite() {
        return ID_Nationalite;
    }

    public void setID_Nationalite(Integer ID_Nationalite) {
        this.ID_Nationalite = ID_Nationalite;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Nationalite{" +
                "ID_Nationalite=" + ID_Nationalite +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
