package tn.essatin.erp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EtatInscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEtatInscription;
    private String nom;

    public EtatInscription(Integer idInscription, String nom) {
        this.idEtatInscription = idInscription;
        this.nom = nom;
    }

    public EtatInscription() {
    }

    public Integer getIdEtatInscription() {
        return idEtatInscription;
    }

    public void setIdEtatInscription(Integer idInscription) {
        this.idEtatInscription = idInscription;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "EtatInscription{" +
                "idInscription=" + idEtatInscription +
                ", nom='" + nom + '\'' +
                '}';
    }
}
