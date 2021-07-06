package tn.essatin.erp.model;

import javax.persistence.*;

@Entity
public class Etudiants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEtudiant;
    @ManyToOne
    private Personne idPersonne;

    public Etudiants(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Etudiants() {
    }

    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Personne getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }

    @Override
    public String toString() {
        return "Etudiants{" +
            "idEtudiant=" + idEtudiant +
            ", idPersonne=" + idPersonne +
            '}';
    }
}
