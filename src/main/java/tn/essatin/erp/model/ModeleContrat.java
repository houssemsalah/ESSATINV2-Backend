package tn.essatin.erp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ModeleContrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idModeleContrat;
    private String libelle;
    private String description;
    private Float Salaire;
    private Float chargeHoraire;

    public ModeleContrat(String libelle, String description, Float salaire, Float chargeHoraire) {
        this.libelle = libelle;
        this.description = description;
        Salaire = salaire;
        this.chargeHoraire = chargeHoraire;
    }

    public ModeleContrat() {
    }

    public Integer getIdModeleContrat() {
        return idModeleContrat;
    }

    public void setIdModeleContrat(Integer idModeleContrat) {
        this.idModeleContrat = idModeleContrat;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getSalaire() {
        return Salaire;
    }

    public void setSalaire(Float salaire) {
        Salaire = salaire;
    }

    public Float getChargeHoraire() {
        return chargeHoraire;
    }

    public void setChargeHoraire(Float chargeHoraire) {
        this.chargeHoraire = chargeHoraire;
    }

    @Override
    public String toString() {
        return "ModeleContrat{" +
                "idModeleContrat=" + idModeleContrat +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                ", Salaire=" + Salaire +
                ", chargeHoraire=" + chargeHoraire +
                '}';
    }
}
