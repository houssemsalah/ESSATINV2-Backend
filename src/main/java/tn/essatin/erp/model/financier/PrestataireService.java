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
    @Enumerated(EnumType.STRING)
    private ETypePrestataire typePrestataire;

    public PrestataireService(Personne idPersonne, ETypePrestataire typePrestataire) {
        this.idPersonne = idPersonne;
        this.typePrestataire = typePrestataire;
    }

    public PrestataireService() {
    }

    public Personne getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Personne idPersonne) {
        this.idPersonne = idPersonne;
    }

    public ETypePrestataire getTypePrestataire() {
        return typePrestataire;
    }

    public void setTypePrestataire(ETypePrestataire typePrestataire) {
        this.typePrestataire = typePrestataire;
    }

    @Override
    public String toString() {
        return "PrestataireService{" +
                "idPrestataire=" + idPrestataire +
                ", idPersonne=" + idPersonne +
                ", typePrestataire=" + typePrestataire +
                '}';
    }
}
