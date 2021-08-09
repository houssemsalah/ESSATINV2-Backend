package tn.essatin.erp.model.financier;

import tn.essatin.erp.model.Personne;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class PrestataireService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private ETypePrestataire typePrestataire;
    @OneToOne
    private Personne personne;

    public PrestataireService(Integer id, ETypePrestataire typePrestataire, Personne personne) {
        this.id = id;
        this.typePrestataire = typePrestataire;
        this.personne = personne;
    }

    public PrestataireService(ETypePrestataire typePrestataire, Personne personne) {
        this.typePrestataire = typePrestataire;
        this.personne = personne;
    }

    public PrestataireService() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ETypePrestataire getTypePrestataire() {
        return typePrestataire;
    }

    public void setTypePrestataire(ETypePrestataire typePrestataire) {
        this.typePrestataire = typePrestataire;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    @Override
    public String toString() {
        return "PrestataireService{" +
                "id=" + id +
                ", typePrestataire=" + typePrestataire +
                ", Personne=" + personne +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrestataireService)) return false;
        PrestataireService that = (PrestataireService) o;
        return getId().equals(that.getId()) &&
                getTypePrestataire() == that.getTypePrestataire() &&
                getPersonne().equals(that.getPersonne());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTypePrestataire(), getPersonne());
    }
}
