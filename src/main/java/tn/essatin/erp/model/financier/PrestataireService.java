package tn.essatin.erp.model.financier;

import tn.essatin.erp.model.Personne;

import javax.persistence.*;

@Entity
public class PrestataireService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrestataire;
    @ManyToOne
    private Personne idPersonne;

    public PrestataireService(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }

    public PrestataireService() {
    }

    public Integer getIdPrestataire() {
        return idPrestataire;
    }

    public void setIdPrestataire(Integer idPrestataire) {
        this.idPrestataire = idPrestataire;
    }

    public Personne getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }

    @Override
    public String toString() {
        return "PrestataireService{" +
                "idPrestataire=" + idPrestataire +
                ", idPersonne=" + idPersonne +
                '}';
    }
}
