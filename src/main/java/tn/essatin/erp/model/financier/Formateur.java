package tn.essatin.erp.model.financier;

import tn.essatin.erp.model.Personne;

import javax.persistence.*;

@Entity
public class Formateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFormateur;
    @ManyToOne
    private Personne idPersonne;


    public Formateur(Personne idPersonne) {
        this.idPersonne = idPersonne;

    }

    public Formateur() {
    }

    public Integer getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(Integer idFormateur) {
        this.idFormateur = idFormateur;
    }

    public Personne getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }


    @Override
    public String toString() {
        return "Formateur{" +
                "idFormateur=" + idFormateur +
                ", idPersonne=" + idPersonne +
                '}';
    }
}
