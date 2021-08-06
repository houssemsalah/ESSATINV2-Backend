package tn.essatin.erp.model.financier;

import tn.essatin.erp.model.Personne;

import javax.persistence.*;

@Entity
public class Vacataire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVacataire;
    @ManyToOne
    private Personne idPersonne;

    public Vacataire(Personne idPersonne ) {
        this.idPersonne = idPersonne;

    }

    public Vacataire() {
    }

    public Integer getIdVacataire() {
        return idVacataire;
    }

    public void setIdVacataire(Integer idVacataire) {
        this.idVacataire = idVacataire;
    }

    public Personne getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }

    @Override
    public String toString() {
        return "Vacataire{" +
                "idVacataire=" + idVacataire +
                ", idPersonne=" + idPersonne +

                '}';
    }
}
